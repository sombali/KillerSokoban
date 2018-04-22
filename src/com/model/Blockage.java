package com.model;

/**
 * Akadályt képező elemet reprezentál
 * Ezek az akadályok nem mozdíthatóak el mezőjükről
 */
public abstract class
Blockage extends Element{
    private boolean stucked;

    /**
     * Játékos szeretné tolni a továbbhaladás irányába az akadályt
     * @param player játékos, ami az akadály mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param s Az objektumok együttes surlódása
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Player player, Direction direction, int s) {
        return false;
    }

    /**
     *
     * @param pushable tolható objektum, ami az akadály mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param s Az objektumok együttes surlódása
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    public boolean hit(Pushable pushable, Direction direction, int s) {
        return false;
    }

}
