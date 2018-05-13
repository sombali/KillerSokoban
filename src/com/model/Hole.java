package com.model;

/**
 * Lyukat reprezentáló mező
 * Ha bármilyen elem lép a lyukra, eltűnik vagy meghal
 */
public class Hole extends Field{





    public HoleView ov = new HoleView();

    public Drawable getView(){
        return  ov;
    }

    /**
     * Megadja a rajta található elemet
     * @return null
     */
    @Override
    public Element getElement(){
        return null;
    }

    /**
     * Eltávolítja magáról a játékostss
     * @param player 
     */
    @Override
    public void removeElement(Element player) {
        setElement(null);
    }

    /**
     * Eltávolítja magáról a tolható elemet
     * @param pushable tolható elem
     */
    @Override
    public void removeElement(Pushable pushable) {
        getWarehouse().removePushable(pushable);
        setElement(null);
    }
    /**
     * Egy elem lép a mezőre
     * @param element
     */
    @Override
    public void stepOnIt(Element element) {
        super.stepOnIt(element);
    }

    /**
     * Eltávolítja a rálépő Pushable elemet, és csökkenti a játékban lévő számukat
     * @param pushable a tolható elem, ami rálépett a mezőre
     */
    @Override
    public void stepOnIt(Pushable pushable) {
        removeElement(pushable);
        getWarehouse().setPushableBoxes(-1);
        if((getWarehouse().getMaxPoints()/10) >= getWarehouse().getPushableBoxes()) getWarehouse().setMaxPoints(-10);
    }

    /**
     * Eltávolítja és megöli a rálépő játékost
     * @param player játékos, ami a mezőre lépett
     */
    @Override
    public void stepOnIt(Player player) {
        player.die();
        removeElement(player);

    }

    /**
     * A kiírást segító függvény
     */
    public void getDescription() {
        System.out.print("O");
    }
}
