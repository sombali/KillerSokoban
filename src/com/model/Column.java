package com.model;

/**
 * Oszlopot reprezentáló osztály
 */
public class Column extends Blockage {

    /**
     * Játékos szeretné tolni a továbbhaladás irányába az oszlopot
     * @param player játékos, ami az oszlop mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param friction Az objektumok együttes surlódása
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Player player, Direction direction, int friction) {
        return false;
    }

    /**
     * Tolható objektum szeretné tolni a továbbhaladás irányába az oszlopot
     * @param pushable a tolható objektum, ami az oszlop mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param friction Az objektumok együttes surlódása
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Pushable pushable, Direction direction, int friction) {
        return false;
    }
}
