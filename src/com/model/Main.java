package com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A Main osztály a main metódus megvalósításáért felelős.
 */
public class Main extends Application {

    static Game game = new Game();

    @Override
    public void start(Stage stage) {

        Canvas canvas = Game.view.canvas;
        canvas.setHeight(1000);
        canvas.setWidth(1000);
        GraphicsContext gc = Game.view.canvas.getGraphicsContext2D();

        Game.view.drawAll();
        Pane root = new Pane();

        root.getChildren().add(canvas);
        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage

        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Drawing Basic Shapes on a Canvas");
        // Display the Stage
        stage.setHeight(945);
        stage.setWidth(1018);
        stage.show();


    }
    /**
     *
     * @param args
     * A program main függvénye, itt történnek a játék során létrejövő objektumok példányosításai, függvényhívásai,
     * illetve egyéb műveletei.
     */


    public static void main(String[] args) throws IOException {

        game.startGame("testmap2.txt");

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String command;
        Player player1 = null;
        Player player2 = null;

        Application.launch(args);





        /*while ((command = br.readLine())!=null) {

           if(command.isEmpty())
               break;

           String[] splittedString = command.split(" ");
           if(splittedString[0].equals("loadmap")) {
               game.startGame(splittedString[1]);
               player1 = game.getWarehouse().player_1;
               player2 = game.getWarehouse().player_2;
           } else if(splittedString[0].equals("stepplayer")) {
            if(splittedString[1].equals("1")) {
                if(splittedString[2].equals("1")) {
                    player1.move(Direction.FIRST);
                } else if(splittedString[2].equals("2")) {
                    player1.move(Direction.SECOND);
                } else if(splittedString[2].equals("3")) {
                    player1.move(Direction.THIRD);
                } else if(splittedString[2].equals("4")) {
                    player1.move(Direction.FOURTH);
                }
            } else if(splittedString[1].equals("2")) {
                if(splittedString[2].equals("1")) {
                    player2.move(Direction.FIRST);
                } else if(splittedString[2].equals("2")) {
                    player2.move(Direction.SECOND);
                } else if(splittedString[2].equals("3")) {
                    player2.move(Direction.THIRD);
                } else if(splittedString[2].equals("4")) {
                    player2.move(Direction.FOURTH);
                }
            }
        } else if(splittedString[0].equals("surrend")) {
            if(splittedString[1].equals("1")) {
                player1.surrender(player2);
            } else if(splittedString[1].equals("2")) {
                player2.surrender(player1);
            }
        } else if(splittedString[0].equals("droptool")) {
            if(splittedString[1].equals("1")) {
                if(splittedString[2].equals("honey")) {
                    player1.throwHoney();
                } else if(splittedString[2].equals("oil")) {
                    player1.throwOil();
                }
            } else if(splittedString[1].equals("2")) {
                if(splittedString[2].equals("honey")) {
                    player2.throwHoney();
                } else if(splittedString[2].equals("oil")) {
                    player2.throwOil();
                }
            }
        } else if(splittedString[0].equals("listplayers")) {
            List<Worker> players = game.getWarehouse().getPlayerList();
            for(int j = 0; j < players.size(); j++) {
                System.out.println(players.get(j).getName() + " " + players.get(j).isAlive() +
                        " " + players.get(j).getPoint() + " " + players.get(j).getHoneyList().size() + " " +
                        players.get(j).getOilList().size());
            }
        } else if(splittedString[0].equals("printpushableboxes")) {
            System.out.println("Pushableboxes: " + game.getWarehouse().getPushableBoxes());
        } else if(splittedString[0].equals("save")) {
            //TODO
        } else if(splittedString[0].equals("load")) {
            //TODO
        }else if (splittedString[0].equals("seeresult")){
            Warehouse wh = game.getWarehouse();
            Field[][] map = game.getWarehouse().getMap();

            for(int i = 0; i < wh.getSizeRow(); i++) {
                for(int j = 0; j < wh.getSizeColumn(); j++) {
                    Element element = map[i][j].getElement();
                    Tools tools = map[i][j].getTools();
                    if(element != null) {
                        element.getDescription();
                    } else if(tools != null) {
                        tools.getDescription();
                    } else {
                        map[i][j].getDescription();
                    }
                }

                System.out.print("\n");
            }
        } else {
            System.out.println("Invalid parancs, írd be újból");
        }

        }*/

    }

}
