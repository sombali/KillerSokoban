package com.model;

/**
 * Falat reprezentáló osztály
 * A pálya szélei fallal vannak körülvéve
 */
public class Wall extends Blockage {

    /**
     * Játékos szeretné tolni a továbbhaladás irányába a falat
     * @param player játékos, ami az fal mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param friction a surlodasi egyutthato
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Player player, Direction direction, int friction) {
        return false;
    }

    /**
     * Játékos szeretné tolni a továbbhaladás irányába a falat
     * @param pushable tolható objektum, ami az fal mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param friction a surlodasi egyutthato
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Pushable pushable, Direction direction, int friction) {
        return false;
    }
}
