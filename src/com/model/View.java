package com.model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A kirajzolás koordinálását végző osztály.
 */
public class View {

    Canvas canvas = new Canvas(600, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    /**
     * Végigveszi az elemeket és kirajzolja őket.
     */
    public void drawAll() {

        Warehouse wh = Main.game.getWarehouse();
        Field[][] map = Main.game.getWarehouse().getMap();

        for (int i = 0; i < wh.getSizeRow(); i++) {
            for (int j = 0; j < wh.getSizeColumn(); j++) {
                Element element = map[i][j].getElement();
                Tools tools = map[i][j].getTools();

                if(element!=null&&tools==null){

                        map[i][j].getView().draw(j*100, i*100);
                        map[i][j].getElement().getView().draw(j * 100, i * 100);

                }
                else if(tools != null&&element!=null) {
                    tools.getView().draw(j * 100, i * 100);
                    map[i][j].getElement().getView().draw(j * 100, i * 100);
                }
                else if(tools!=null&&element==null) {
                    tools.getView().draw(j * 100, i * 100);
                }
                else{
                    map[i][j].getView().draw(j*100, i*100);
                }
                //element.draw(j*100, i*100);

               // if(element != null) element.getView().draw(j*100, i*100);
            }

        }

    }
}
