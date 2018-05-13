package com.model;

import javafx.scene.image.Image;

public class BoxView implements Drawable {

    private Image img = new Image("file:box.jpg");

    public Image getImage() {
        return img;
    }

    public void draw(int x, int y) {
        Game.view.gc.drawImage(img, x, y, 90, 90);
    }

}
