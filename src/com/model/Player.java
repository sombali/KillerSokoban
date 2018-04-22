package com.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Az összes (még jövőben lehetséges) játékos közös attribútimait, metódusait foglalja magában.
 */

public abstract class Player extends Element{
    /**
     * A játékos nevét tárolja.
     */
    private String name;

    /**
     * A jatekos nevenek beallitasa
     * @param name a jatekos neve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param point A játékos pontját tárolja.
     */
    private int point = 0;

    /**
     * A jatekos ereje.
     */
    private int strength;

    /**
     * Jatekos erejenek lekerdezese
     * @return jatekos ereje
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Jatekos erejenek beallitasa
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * A jatekos altal tartalmazott mezek.
     */
    private List<Honey> honey = new ArrayList<>();

    /**
     * A jatekos altal tartalmazott olajak.
     */
    private List<Oil> oil = new ArrayList<>();

    public Player() {
        setStrength(12);
        for(int i = 0; i < 3; i++) {
            honey.add(new Honey());
            oil.add(new Oil());
        }
    }
    /**
     * Ezzel rakjuk le a megfelelo mezore a Mezet
     */
    public void throwHoney(){
        Tools h= getHoney();
        field.setTools(h);
        h.setField(this.field);
    }
    /**
     * Ezzel rakjuk le a megfelelo mezore az Olajat
     */
    public void throwOil(){
        Tools h=getOil();
        field.setTools(h);
        h.setField(this.field);
    }
    /**
     * Visszaad egy mezet es torli a listabol.
     * @return mez
     */
    public Honey getHoney() {
        Honey h = honey.get(0);
        honey.remove(0);
        return h;
    }

    /**
     * Visszaad egy olajat es torli a listabol.
     * @return olaj
     */
    public Oil getOil() {
        Oil o = oil.get(0);
        oil.remove(0);
        return o;
    }

    //mezek szamanak kiiratasahoz kell
    public List<Honey> getHoneyList() {
        return honey;
    }

    //olajak szamanak kiiratasahoz kell
    public List<Oil> getOilList() {
        return oil;
    }

    /**
     *
     * @param direction Az adott irány amelybe a játékos lépni fog.
     *                  A játékos mozgását valósítja meg.
     */
    public void move(Direction direction) {
        //...
    }

    /**
     * A jatekos pontjainak lekerdezese
     * @return
     */
    public int getPoint() {
        return point;
    }

    /**
     * Jatekos nevenek lekerdezese
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Ha meghal a játékos(pl: lyukba lép) akkor hívódik meg.
     */
    public void die() {
        getField().getWarehouse().removePlayer(this);
    }

    /**
     *
     * @param point A játékos pontszámának megváltoztatása a legfőbb feladata a függvénynek.
     */
    public void addPoints(int point) {
        this.point += point;
    }

    /**
     * Minden játékos rendelkezik a feladás lehetőségével,
     * mikor élni kíván vele egy játékos ez a metódus hívódik meg.
     */
    public void surrender() {
        ArrayList<Worker> players = getField().getWarehouse().getPlayerList();
        if(this.equals(players.get(0))) {
            players.get(1).win();
        } else {
            players.get(0).win();
        }
    }

    /**
     * A függvény meghívódik ha az egyik játékos nyert, tehát az összes saját területén láda áll.
     */
    public void win() {
        //TODO
    }

    /**
     *
     * @param pushable Egy tolható objektum.
     * @param direction Egy adott irány.
     * @param s A tárgyak együtte surlódása
     * @return true-val tér vissza ha a lépés sikeres volt, false-szal ha nem.
     */
    public boolean hit(Pushable pushable, Direction direction, int s) {
        Field nextfield = getField().getNeighbors(direction);
        Element element = nextfield.getElement();

        if(element != null) {
            this.getField().removeElement(this);
            die();
            return true;
        } else {
            step(nextfield);
            return true;
        }
    }


    /**
     *
     * @param player Egy játékos objektum
     * @param direction Egy adott irány
     * @return false-al tér vissza
     */
    public boolean hit(Player player, Direction direction, int s) {
        return false;
    }

    /**
     *
     * @param nextField az a mező amelyre az adott Player lépni fog.
     * A step metódus beállítja a következő mezőt a játékos mezőjének,
     * és meghívja ennek a mezőnek a stepOnIt függvényét ami a mezőre
     * rálépéskor kiváltó eseményt indítja el.
     */
    //ideraktam a Workerbol a stepet, gondolvan hogy csak valositsa meg ezt @Bazsi (Zsir)
    public void step(Field nextField) {
        field.removeElement(this);
        nextField.acceptElement(this);
        nextField.stepOnIt(this);
    }

    public void getDescription() {

    }

}
