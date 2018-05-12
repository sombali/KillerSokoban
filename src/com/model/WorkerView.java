package com.model;

import javafx.scene.image.Image;

public class WorkerView implements Drawable {
    private Image img = new Image("file:worker_1_final.png");
    private Image img2=new Image("file:worker_2_final.png");
    Worker w;
    public Image getImage() {
        return img;

    }

    public void draw(int x, int y) {
        if(w.getName().equals("1"))
        Game.view.gc.drawImage(img, x, y, 100, 100);
        else
            Game.view.gc.drawImage(img2, x, y, 100, 100);

    }
}
