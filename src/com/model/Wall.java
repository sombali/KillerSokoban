package com.model;

/**
 * Falat reprezentáló osztály
 * A pálya szélei fallal vannak körülvéve
 */
public class Wall extends Blockage {

    public WallView wv = new WallView();

    public WallView getView() {


        return wv;

    }

    /**
     * Játékos szeretné tolni a továbbhaladás irányába a falat
     * @param player játékos, ami az fal mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param s átadott erő
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Player player, Direction direction, int s) {
        return false;
    }

    /**
     * Játékos szeretné tolni a továbbhaladás irányába a falat
     * @param pushable tolható objektum, ami az fal mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @param s átadott erő
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Pushable pushable, Direction direction, int s) {
        return false;
    }

    public String getDescription() {
        System.out.print("#");
        return "";
    }
}
