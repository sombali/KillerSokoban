package com.model;

import javafx.scene.image.Image;

/**
 * Az Olaj kirajzolásáért felelős osztály.
 */
public class OilView implements Drawable {
    /**
     * Az Olaj képét eltároló objektum.
     */
    private Image img = new Image("file:oil.png");

    /**
     *
     * @return Az Olaj képe.
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
