package com.model;

/**
 * Tolható objektumot reprezentáló osztály
 */
public abstract class Pushable extends Element {

    /**
     * Jelzi, hogy az objektum semelyik irányba sem mozdítható
     */
    private boolean stucked;

    /**
     * A mozgathato objektum sulya.
     */
    private int weight;

    /**
     * Játékos szeretné tolni a tolható elemet a továbbhaladás irányába.
     * @param player játékos, ami a tolható elem helyére szeretne lépni
     * @param direction a játékos haladási iránya
     * @return lépés sikeressége
     */
    public boolean hit(Player player, Direction direction,int s) {

        Field nextField = getField().getNeighbors(direction);
        Element element1 = nextField.getElement();
        //Itt alltijuk be az erot.

        int remainingForce;
        if (field.getTools() != null) {
            int tool = field.getTools().getChangeFriction();
            remainingForce = s - (weight + tool);
        } else
            remainingForce = s - weight;

        /*
         * Ha nem üres a szomszéd mező, megnézi, hogy az az elem tovább tud tolódni,
         * ha sikerült, a tolható elem is továbblép és jelzi, hogy sikerült lépnie.
         * Ha üres a szomszéd mező, továbblép, és jelzi a lépés sikerességét
         */

        boolean allowed = true;
        if(remainingForce>=0) {
            if (element1 != null) {
                allowed = element1.hit(this, direction, remainingForce);
                if (allowed) {
                    step(nextField);
                }
            } else {
                step(nextField);

                return true;
            }

            return allowed;
        }
        else
            return false;
    }

    /**
     * Egy másik tolható elem szeretné tolni az elemet a továbbhaladás irányába.
     * @param pushable az új elem, ami a jelenlegi tolható elem helyére szeretne lépni
     * @param direction az elem továbbhaladási iránya
     * @return lépés sikeressége
     */
    public boolean hit(Pushable pushable, Direction direction,int s) {
        Field nextField = getField().getNeighbors(direction);
        Element nextElement1 = nextField.getElement();
        int remainingForce;
        if (field.getTools() != null) {
            int tool = field.getTools().getChangeFriction();
            remainingForce = s - (weight + tool);
        } else
            remainingForce = s - weight;
        /*
         * Ha nem üres a szomszéd mező, megnézi, hogy az az elem tovább tud tolódni,
         * ha sikerült, a tolható elem is továbblép és jelzi, hogy sikerült lépnie.
         * Ha üres a szomszéd mező, továbblép, és jelzi a lépés sikerességét
         */
        boolean allowed = true;

        if (remainingForce >= 0) {
            if (nextElement1 != null) {
                allowed = nextElement1.hit(this, direction,remainingForce);
                if (allowed) {
                    step(nextField);
                }
            } else {
                step(nextField);
                return true;
            }

            return allowed;
        }
        else
            return false;
    }

    /**
     * Rálép a szomszédos mezőre
     * @param nextField a szomszédos mező
     */
    public void step(Field nextField) {
        getField().removeElement(this);
        nextField.acceptElement(this);
        stuck();
        nextField.stepOnIt(this);
    }


    public void stuck() {

    }
}
