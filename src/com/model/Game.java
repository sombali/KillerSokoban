package com.model;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A játékot kezelő osztály
 *
 * Elindítja és befejezi a játékot, inicializálja a pályát. A játéknak vége van,
 * ha egy játékos minden kijelölt mezőjére ládát tolt, vagy ha elfogytak a tolható ládák.
 */
public class Game
{
    static View view = new View();

    /**
     * A játék pályája
     */
    Warehouse warehouse = new Warehouse();

    /**
     * Elindítja a játékot
     */
    public void startGame(String map) throws IOException {
        warehouse.initialize(map);
        warehouse.setGame(this);
    }

    /**
     * Összehasonlítja a játékosok pontjait, és a legtöbb pontot gyűjtő játékos megnyeri a játékot
     */
    public void endGame() {
        ArrayList<Worker> players = warehouse.getPlayerList();
        Worker player1 = warehouse.player_1;
        Worker player2 = warehouse.player_2;
        if(player1.getPoint() == player2.getPoint() ) {
            System.out.println("Tie!");
            Main.winner("Dontetlen!");
            System.exit(0);
        } else if(player1.getPoint() > player2.getPoint()) {
            player1.win();
        } else if(!player1.isAlive() && !player2.isAlive()) {
            if(player1.getPoint() > player2.getPoint()) {
                player1.win();
            } else if( player1.getPoint() == player2.getPoint()) {
                System.out.println("Tie!");
                Main.winner("Dontetlen!");
                System.exit(0);
            } else {
                player2.win();
            }
        } else {
            player2.win();
        }
    }


    /**
     * Megadja a játék pályáját
     * @return játék pályája
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }
}

