package com.model;

import javafx.scene.image.Image;

public class TrapDoorView implements Drawable {
    /**
     * A nyitott TrapDoor képét eltároló objektum.
     */
    private Image img = new Image("file:trapdoor_closed.jpg");
    /**
     * A zárt TrapDoor képét eltároló objektum.
     */
    private Image img2 = new Image("file:hole.jpg");
    TrapDoor h;

    /**
     *
     * @return A TrapDoor képe.
     */
    public Image getImage() {
        return img;
    }
    /**
     * @param x Kirajzolási x koordináta.
     * @param y Kirajzolási y koordináta.
     */
    public void draw(int x, int y) {
        TrapDoorState tr;
        if(h.getState()==TrapDoorState.OPENED)
            Game.view.gc.drawImage(img2, x, y, 100, 100);

        else
            Game.view.gc.drawImage(img, x, y, 100, 100);

    }

}
