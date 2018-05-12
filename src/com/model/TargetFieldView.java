package com.model;

import javafx.scene.image.Image;

public class TargetFieldView implements Drawable {

    private Image img = new Image("file:targetfield_1.png");
    private Image img2 = new Image("file:targetfield_2.png");

    TargetField t;
    public Image getImage() {
        return img;
    }

    public void draw(int x, int y) {

        if(t.getPlayer().getName().equals("1"))
        Game.view.gc.drawImage(img, x, y, 100, 100);
        else
            Game.view.gc.drawImage(img2, x, y, 100, 100);

    }
}
