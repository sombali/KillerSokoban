package com.model;

import javafx.scene.image.Image;

/**
 * A TargetFieldek kirajzolásáért felelős osztály.
 */
public class TargetFieldView implements Drawable {
    /**
     * Az egyes játékoshoz tartozó TargetField képe.
     */
    private Image img = new Image("file:targetfield_1.png");
    /**
     * A kettes játékoshoz tartozó TargetField képe.
     */
    private Image img2 = new Image("file:targetfield_2.png");

    TargetField t;

    /**
     *
     * @return A TargetField képe.
     */
    public Image getImage() {
        return img;
    }
    /**
     * @param x Kirajzolási x koordináta.
     * @param y Kirajzolási y koordináta.
     */
    public void draw(int x, int y) {

        if(t.getPlayer().getName().equals("1"))
        Game.view.gc.drawImage(img, x, y, 50, 50);
        else
            Game.view.gc.drawImage(img2, x, y, 50, 50);

    }
}
