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
    private int weight = 3;

    /**
     * Játékos szeretné tolni a tolható elemet a továbbhaladás irányába. Először is megvizsgálja van-e az adott irányban mező,
     * majd megvizsgálja, hogy a játékos ereje nagyobb-e mint a doboz súlya. Ha ezek egyike sem teljesül, false-t ad vissza,
     * a mozgatás adott irányba nem sikerült. Ha viszont a feltételek teljesülnek, következő lépésben megvizsgálja, hogy a szomszédos mezőn áll element.
     * Ha üres, egyszerűen továbblép és true-t ad vissza, ha nem üres, a függvény továbbhívódik,
     * és paraméterben továbbadja a megmaradt erőt.
     * @param player játékos, ami a tolható elem helyére szeretne lépni
     * @param direction a játékos haladási iránya
     * @param strength a játékos ereje
     * @return lépés sikeressége
     */
    public boolean hit(Player player, Direction direction, int strength) {

        Field nextField = getField().getNeighbors(direction);
        if (nextField != null) {
            int remainingForce;
            if (field.getTools() != null) {
                int tool = field.getTools().getChangeFriction();
                remainingForce = strength - (weight + tool);
            } else
                remainingForce = strength - weight;

            if (remainingForce >= 0) {
                Element element1 = nextField.getElement();

                /*
                 * Ha nem üres a szomszéd mező, megnézi, hogy az az elem tovább tud tolódni,
                 * ha sikerült, a tolható elem is továbblép és jelzi, hogy sikerült lépnie.
                 * Ha üres a szomszéd mező, továbblép, és jelzi a lépés sikerességét
                 */
                boolean allowed = true;
                if (element1 != null) {
                    allowed = element1.hit(this, direction, remainingForce);
                    if (allowed) step(nextField);
                } else {
                    step(nextField);
                }
                return allowed;
            }

            return false;

        }

        return false;
    }


    /**
     * Egy másik tolható elem szeretné tolni az elemet a továbbhaladás irányába. Először is megvizsgálja van-e az adott irányban mező,
     * majd megvizsgálja, hogy az objektumok ereje nagyobb-e mint a doboz súlya. Ha ezek egyike sem teljesül, false-t ad vissza,
     * a mozgatás adott irányba nem sikerült. Ha viszont a feltételek teljesülnek, következő lépésben megvizsgálja, hogy a szomszédos mezőn áll element.
     * Ha üres, egyszerűen továbblép és true-t ad vissza, ha nem üres, a függvény továbbhívódik,
     * és paraméterben továbbadja a megmaradt erőt
     * @param pushable az új elem, ami a jelenlegi tolható elem helyére szeretne lépni
     * @param direction az elem továbbhaladási iránya
     * @param s a surlódási együttható
     * @return lépés sikeressége
     */
    public boolean hit(Pushable pushable, Direction direction, int s) {

        Field nextField = getField().getNeighbors(direction);

        if (nextField != null) {
            int remainingForce;

            if (field.getTools() != null) {
                int tool = field.getTools().getChangeFriction();
                remainingForce = s - (weight + tool);
            } else
                remainingForce = s - weight;

            if (remainingForce >= 0) {
                Element element1 = nextField.getElement();

                /*
                 * Ha nem üres a szomszéd mező, megnézi, hogy az az elem tovább tud tolódni,
                 * ha sikerült, a tolható elem is továbblép és jelzi, hogy sikerült lépnie.
                 * Ha üres a szomszéd mező, továbblép, és jelzi a lépés sikerességét
                 */
                boolean allowed = true;
                if (element1 != null) {
                    allowed = element1.hit(this, direction, remainingForce);
                    if (allowed) step(nextField);
                } else {
                    step(nextField);
                    return true;
                }
                return allowed;
            }

            return false;
        }

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
