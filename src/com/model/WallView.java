package com.model;

import javafx.scene.image.Image;

/**
 * A Wall kirajzolásáért felelős osztály.
 */
public class WallView implements Drawable {
    /**
     * A Wall képét elmentő objektum.
     */
    private Image img = new Image("file:wall.jpg");

    /**
     *
     * @return A Wall képe.
     */
    public Image getImage() {
        return img;
    }
    /**
     * @param x Kirajzolási x koordináta.
     * @param y Kirajzolási y koordináta.
     */
    public void draw(int x, int y) {
        Game.view.gc.drawImage(img, x, y, 100, 100);
    }

}
