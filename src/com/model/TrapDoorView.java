package com.model;

import javafx.scene.image.Image;

public class TrapDoorView implements Drawable {

    private Image img = new Image("file:trapdoor_closed.jpg");
    private Image img2 = new Image("file:hole.jpg");
    TrapDoor h;


    public Image getImage() {
        return img;
    }

    public void draw(int x, int y) {
        TrapDoorState tr;
        if(h.getState()==TrapDoorState.OPENED)
            Game.view.gc.drawImage(img2, x, y, 100, 100);

        else
            Game.view.gc.drawImage(img, x, y, 100, 100);

    }

}
