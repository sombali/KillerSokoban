package com.model;

import javafx.scene.image.Image;

/**
 * A dolgozók kirajzolásáért felelős osztály.
 */
public class WorkerView implements Drawable {
    //private Image img = new Image("file:worker_1_final.png");

    /**
     * Az egyik dolgozó képét eltároló objektum.
     */
    private Image img = new Image("file:en.png");
    /**
     * A másik dolgozó képét eltároló objektum.
     */
    private Image img2=new Image("file:worker_2_final.png");
    Worker w;

    /**
     *
     * @return A Worker képe
     */
    public Image getImage() {
        return img;

    }
    /**
     * @param x Kirajzolási x koordináta.
     * @param y Kirajzolási y koordináta.
     */
    public void draw(int x, int y) {
        if(w.getName().equals("1"))
        Game.view.gc.drawImage(img, x, y, 40, 40);
        else
            Game.view.gc.drawImage(img2, x, y, 40, 40);

    }
}
