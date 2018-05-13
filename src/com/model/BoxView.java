package com.model;

import javafx.scene.image.Image;

/**
 * A Box kirajzolásáért felelős osztály.
 */
public class BoxView implements Drawable {
    /**
     * A Box képét tárolja.
     */
    private Image img = new Image("file:box.jpg");

    /**Visszatér a Box képével.
     *
     * @return Box képe
     */
    public Image getImage() {
        return img;
    }

    /**
     *
     * @param x kirajzolási x koordináta
     * @param y kirajzolási y koordináta
     */
    public void draw(int x, int y) {
        Game.view.gc.drawImage(img, x, y, 40, 40);
    }

}
