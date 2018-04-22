package com.model;

/**
 * Lyukat reprezentáló mező
 * Ha bármilyen elem lép a lyukra, eltűnik vagy meghal
 */
public class Hole extends Field{

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
    public void removeElement(Player player)
    {
        System.out.println("-->[Hole: h].removeElement(worker)");
    }

    /**
     * Eltávolítja magáról a tolható elemet
     * @param pushable tolható elem
     */
    @Override
    public void removeElement(Pushable pushable)
    {
        System.out.println("-->[Hole: h].removeElement(box)");
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
        System.out.println("->[Hole: h].stepOnIt(box)");
        System.out.println("Elpusztult a doboz!");
        removeElement(pushable);
        new Warehouse().setPushableBoxes(-1);
        System.out.println("<-");
    }

    /**
     * Eltávolítja és megöli a rálépő játékost
     * @param player játékos, ami a mezőre lépett
     */
    @Override
    public void stepOnIt(Player player) {
        System.out.println("->[Hole: h].stepOnIt(worker)");
        removeElement(player);
        player.die();
        System.out.println("<-");
    }
}
