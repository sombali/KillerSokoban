package com.model;

import javafx.scene.image.Image;

public class SwitchView implements Drawable {

    private Image img = new Image("file:switch.png");

    public Image getImage() {
        return img;
    }

    public void draw(int x, int y) {
        Game.view.gc.drawImage(img, x, y, 100, 100);
    }

}
