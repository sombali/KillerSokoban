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
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Player player, Direction direction,int s) {
        //System.out.println("-->[Wall :w].hit(worker, direction)");
       // System.out.println("<--false == Nem tudsz ide Lepni");
        return false;
    }

    /**
     * Játékos szeretné tolni a továbbhaladás irányába a falat
     * @param pushable tolható objektum, ami az fal mezőjére kíván lépni
     * @param direction a továbbhaladás iránya
     * @return mindig false-al tér vissza, mert Blockage elem nem mozgatható
     */
    @Override
    public boolean hit(Pushable pushable, Direction direction,int s) {
      //  System.out.println("-->[Wall :w].hit(box, direction)");
       // System.out.println("<--false == Nem tudsz ide Lepni");
        return false;
    }
}
