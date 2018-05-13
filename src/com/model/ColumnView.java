package com.model;

import javafx.scene.image.Image;

/**
 * Az oszlop kirajzolásáért felelős osztály.
 */
public class ColumnView implements Drawable {
    /**
     * Az oszlop képét tároló objektum.
     */
    private Image img = new Image("file:column.jpg");

    /**
     * Visszatér az oszlop képével.
     *
     * @return Az oszlop képe.
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
        Game.view.gc.drawImage(img, x, y, 100, 100);
    }

}

