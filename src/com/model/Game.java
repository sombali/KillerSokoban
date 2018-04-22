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
    /**
     * A játék pályája
     */
    Warehouse warehouse = new Warehouse();

    /**
     * Elindítja a játékot
     */
    public void startGame(String map) throws IOException {
        warehouse.initialize(map);
    }

    /**
     * Befejezi a játékot
     */
    public void endGame() {
        ArrayList<Player> players = warehouse.getPlayerList();
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        if(player1.getPoint() == player2.getPoint()) {
            //TODO
        } else if(player1.getPoint() > player2.getPoint()) {
            player1.win();
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

