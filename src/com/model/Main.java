package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * A Main osztály a main metódus megvalósításáért felelős.
 */
public class Main extends Application {

    static Game game = new Game();
    private static MediaPlayer mediaPlayer;


    private static final String musicFile = "muzsika.mp3";

    private boolean flag=false;
    private Stage window;
    private Scene scene ;
    private Button button;
    private Button button2;
    private Label label;
    ListView<String> listView;


    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Killer Sokoban - Menu");
        listView = new ListView<>();
        button = new Button("Submit");
        button2 = new Button("Help");
        label = new Label("Choose a map ! ");
        button.setPrefWidth(150);
        button2.setPrefHeight(20);
        button2.setPrefWidth(150);
        listView.getItems().addAll("testmap2.txt","testmap3.txt","testmap4.txt");
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        button.setOnAction(e->buttonClicked(stage));
        button2.setOnAction(e->buttonClicked2());
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(label,listView,button,button2);
        scene = new Scene(layout,300,300);
        window.getIcons().add(new Image("file:boci.png"));
        window.setScene(scene);
        window.show();

    }

    private void buttonClicked(Stage stage){
        String map;
        if(listView.getSelectionModel().getSelectedItem()== null)
            return;
        map = listView.getSelectionModel().getSelectedItem();
        initGame(stage,map);
    }
    private void buttonClicked2(){
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label label = new Label("Lama");
        pane.getChildren().addAll(label);
        Scene scene = new Scene(pane,300,300);
        stage.setTitle("Helper");
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Killer Sokoban");

        // Display the Stage
        stage.setHeight(250);
        stage.setWidth(250);

        stage.show();

    }
    public void initGame(Stage stage,String map) {
        {
            try {
                game.startGame(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Canvas canvas = Game.view.canvas;
            canvas.setHeight(750);
            canvas.setWidth(750);
            GraphicsContext gc = Game.view.canvas.getGraphicsContext2D();

            Game.view.drawAll();
            Pane root = new Pane();

            root.getChildren().add(canvas);
            // Create the Scene
            Scene scene = new Scene(root);
            // Add the Scene to the Stage

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            game.warehouse.player_1.move(Direction.SECOND);
                            break;
                        case RIGHT:
                            game.warehouse.player_1.move(Direction.THIRD);
                            break;
                        case DOWN:
                            game.warehouse.player_1.move(Direction.FOURTH);
                            break;
                        case LEFT:
                            game.warehouse.player_1.move(Direction.FIRST);
                            break;
                        case L:
                            game.warehouse.player_1.throwOil();
                            break;
                        case K:
                            game.warehouse.player_1.throwHoney();
                            break;

                        case W:
                            game.warehouse.player_2.move(Direction.SECOND);
                            break;
                        case A:
                            game.warehouse.player_2.move(Direction.FIRST);
                            break;
                        case S:
                            game.warehouse.player_2.move(Direction.FOURTH);
                            break;
                        case D:
                            game.warehouse.player_2.move(Direction.THIRD);
                            break;
                        case Q:
                            game.warehouse.player_2.throwHoney();
                            break;
                        case E:
                            game.warehouse.player_2.throwOil();
                            break;
                    }
                    Game.view.drawAll();
                    Warehouse wh = game.getWarehouse();
                    Field[][] map = game.getWarehouse().getMap();
        /*
                    for (int i = 0; i < wh.getSizeRow(); i++) {
                        for (int j = 0; j < wh.getSizeColumn(); j++) {
                            Element element = map[i][j].getElement();
                            Tools tools = map[i][j].getTools();
                            if (element != null) {
                                element.getDescription();
                            } else if (tools != null) {
                                tools.getDescription();
                            } else {
                                map[i][j].getDescription();
                            }
                        }

                        System.out.print("\n");
                    }*/
                }
            });

            stage.setScene(scene);
            // Set the Title of the Stage
            stage.setTitle("Killer Sokoban");
            stage.getIcons().add(new Image("file:boci.png"));
            // Display the Stage
            stage.setHeight(750);
            stage.setWidth(750);
            stage.show();

            // playMusic(musicFile);
        }
    }

    public static void winner(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vege a jateknak!");
        alert.setHeaderText("Jatek vege");
        alert.setContentText(string);
        alert.showAndWait();
    }

    /**
     * Zene elindítása.
     * @param musicFile
     */
    public void playMusic(String musicFile) {
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     *
     * @param args
     * A program main függvénye, itt történnek a játék során létrejövő objektumok példányosításai, függvényhívásai,
     * illetve egyéb műveletei.
     */


    public static void main(String[] args)throws  IOException  {




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