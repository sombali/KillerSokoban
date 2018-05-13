package com.model;

/**
 * A pályán szereplő dobozokat reprezentáló osztály.
 */
public class Box extends Pushable{

    /**
     * Játékos szeretné tolni tovább a ládát
     * @param player játékos, ami a tolható elem helyére szeretne lépni
     * @param direction a játékos haladási iránya
     * @param friction Az objektumok együttes surlódása
     * @return true-val tér vissza ha a lépés sikeres volt, false-szal ha nem
     */
    @Override
    public boolean hit(Player player, Direction direction, int friction) {
        return super.hit(player, direction, friction);
    }

    public BoxView vw = new BoxView();

    public Drawable getView() {
        return vw;

    }
    /**
     * Egy tolható objektum tolja tovább a ládát
     * @param pushable az új elem, ami a jelenlegi tolható elem helyére szeretne lépni
     * @param direction az elem továbbhaladási iránya
     * @param friction Az objektumok együttes surlódása
     * @return true-val tér vissza ha a lépés sikeres volt, false-szal ha nem
     */
    @Override
    public boolean hit(Pushable pushable, Direction direction, int friction) {
        return super.hit(pushable, direction, friction);
    }

    public String getDescription() {
        System.out.print("x");
        return "Box";
    }
}

