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
import javafx.scene.image.ImageView;
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

    private String name1;
    private String name2;


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

        listView.getItems().addAll("map1.txt","map2.txt","map3.txt");
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
        VBox vb = new VBox(10);
        Label label = new Label("Az első játékos a nyilakkal tud navigálni, az L billentyű megnyomásával olajat, a K billentyű pedig mézet tud eldobni.\n"+
                " A második játékos a WASD billentyűkkel tud navigálni, az E billentyűvel olajat, a Q-val pedig mézet dob.\n" +
                "Egy játékos 3 olajjal és 3 mézzel rendelkezik. Játékosnak annyi ereje van, hogy egyszerre 3 ládát tud eltolni. \n" +
                "A játéknak csak akkor van vége:\n" +
                "        1.     ha nincs több tolható láda\n" +
                "        2.     ha egy játékos megszerezte a maximális pontok számát(ez a saját célmezők számával egyenlő - \n"+
                "               de ha kevesebb tolható láda van, akkor értelemszerűen ez változik)\n" +
                "        3.     ha mindkét játékos meghalt\n");

        Button btn = new Button ("Close");
        btn.setOnAction(e->Buton3Clicked(stage));



        Image player1 = new Image("file:en.png");
        Image player2 = new Image("file:worker_2_final.png");
        Image tf1 = new Image("file:targetfield_1.png");
        Image tf2 = new Image("file:targetfield_2.png");
        ImageView iv1=new ImageView(player1);
        ImageView iv2=new ImageView(player2);
        ImageView iv3=new ImageView(tf1);
        ImageView iv4=new ImageView(tf2);

        iv1.setFitHeight(50);
        iv1.setFitWidth(50);
        iv2.setFitHeight(50);
        iv2.setFitWidth(50);
        iv3.setFitHeight(50);
        iv3.setFitWidth(50);
        iv4.setFitHeight(50);
        iv4.setFitWidth(50);

        Label label1 = new Label("Megfelelő Célmezők : ");
        Label label2 = new Label("==========================");
        vb.setPadding(new Insets(20,20,20,20));
        vb.getChildren().addAll(label,label1,iv1,iv3,label2,iv2,iv4,btn);

        Scene scene = new Scene(vb,300,300);

        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Help!");


        // Display the Stage
        stage.setHeight(580);
        stage.setWidth(750);

        stage.show();

    }

    private void Buton3Clicked(Stage stage)
    {
        stage.close();
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

            //game.getWarehouse().player_1.setName(name1);
            //game.getWarehouse().player_2.setName(name2);

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
                        case M: game.warehouse.player_1.surrender(game.warehouse.player_2);
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
                        case Z: game.warehouse.player_2.surrender(game.warehouse.player_1);
                            break;
                    }
                    Game.view.drawAll();
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

            //playMusic(musicFile);
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
     * Az applikáció elindítása
     *
     */


    public static void main(String[] args)throws  IOException  {

        Application.launch(args);


    }

}