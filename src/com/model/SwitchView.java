package com.model;

import javafx.scene.image.Image;

/**
 * A Switch kirajzolásáért felelős osztály.
 */
public class SwitchView implements Drawable {
    /**
     * A Switch képét eltároló objektum.
     */
    private Image img = new Image("file:switch.png");

    /**
     *
     * @return A Switch képe.
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
