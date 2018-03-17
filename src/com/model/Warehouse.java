package com.model;



import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private int pushableBoxes;
    private List<Field> fields;
    private List<Player> playerList;


    public void initialize() {
        System.out.println("Munkas beallitasa elso mezore: ");
        this.fields = new ArrayList<>();
        this.playerList = new ArrayList<>();
        Field playerField = new Field();
        createPlayer();
        playerField.setElement(playerList.get(0));
        this.fields.add(playerField);
        playerList.get(0).setField(playerField);
    }

    public void addField(Field field) {
        this.fields.add(field);
    }

    public void createPlayer() {
        playerList.add(new Worker());
    }

    public void removePlayer() {

    }

    public void setPushableBoxes(int num) {
        pushableBoxes -= num;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
