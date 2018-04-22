package com.model;

/**
 * A pályán szereplő dobozokat reprezentáló osztály.
 */
public class Box extends Pushable{

    /**
     * Játékos szeretné tolni tovább a ládát
     * @param player játékos, ami a tolható elem helyére szeretne lépni
     * @param direction a játékos haladási iránya
     * @return true-val tér vissza ha a lépés sikeres volt, false-szal ha nem
     */
    @Override
    public boolean hit(Player player, Direction direction,int s) {
        return super.hit(player, direction,s);
    }

    /**
     * Egy tolható objektum tolja tovább a ládát
     * @param pushable az új elem, ami a jelenlegi tolható elem helyére szeretne lépni
     * @param direction az elem továbbhaladási iránya
     * @return true-val tér vissza ha a lépés sikeres volt, false-szal ha nem
     */
    @Override
    public boolean hit(Pushable pushable, Direction direction,int s) {
        return super.hit(pushable, direction,s);
    }
}

