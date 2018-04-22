package com.model;

/**
 * A kijelölt mező, amire a ládákat tolni kell
 * Ha a mezőre láda lép, játékosa pontot kap, ha a mezőről a láda eltolódik, a játékos pontot veszít
 */
public class TargetField extends Field {

    /**
     * A mezőhöz tartozó játékos
     */
    private Player player;

    /**
     * A mezőhöz tartozó játékos beállítása
     * @param player a mezőhöz tartozó játékos
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * A mezőhöz tartozó játékos lekérdezése
     * @return a mezőhöz tartozó játékos
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * A mezőre tolható elem lép, a játékos pontot kap
     * @param pushable a mezőre lépett tolható elem
     */
    @Override
    public void stepOnIt(Pushable pushable) {
        player.addPoints(10);
    }

    /**
     * A mezőről odébbtolódik a tolható elem, a játékos pontot veszít
     * @param pushable a mezőről lelépő tolható elem
     */
    public void removeElement(Pushable pushable) {
        super.removeElement(pushable);
        player.addPoints(-10);
    }
}
