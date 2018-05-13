package com.model;

import javafx.scene.image.Image;

/**
 * A Méz kirajzolásáért felelős osztály.
 */
public class HoneyView implements Drawable {
    /**
     * A Méz képét eltároló objektum.
     */
    private Image img = new Image("file:honey.png");

    /**
     *
     * @return Az Méz képe
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
