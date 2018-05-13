package com.model;

import javafx.scene.image.Image;

/**
 * A Hole függvény kirajzolásáért felelős osztály.
 */
public class HoleView implements Drawable {
    /**
     * A Hole képét eltároló objektum.
     */
    private Image img = new Image("file:hole.jpg");

    /**
     *
     * @return A Hole képe.
     */
    public Image getImage() {
        return img;
    }
    /**
     *
     * @param x Kirajzolási x koordináta.
     * @param y Kirajzolási y koordináta.
     */
    public void draw(int x, int y) {
        Game.view.gc.drawImage(img, x, y, 50, 50);
    }

}
