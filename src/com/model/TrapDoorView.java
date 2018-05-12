package com.model;

import javafx.scene.image.Image;

public class TrapDoorView implements Drawable {

    private Image img = new Image("file:trapdoor_closed.jpg");

    public Image getImage() {
        return img;
    }

    public void draw(int x, int y) {
        Game.view.gc.drawImage(img, x, y, 100, 100);
    }

}
