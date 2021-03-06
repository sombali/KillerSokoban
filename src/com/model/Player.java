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
     * A jatekos jatekbeli nevet tarolja
     */
    private String IGName;
    /**
     * életben van-e a játékos vagy sem
     */
    private boolean alive = true;

    /**
     * Lekérdezi, hogy a játékos életben, azaz játékban van-e még
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * A jatekos nevenek beallitasa
     * @param name a jatekos neve
     */
    public void setName(String name) {
        this.name = name;
    }
    public void setIGName(String igname){this.IGName = igname;}
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

    /**
     * Játékos konstruktora, minden játékos 12 egységnyi erővel és 3-3 toolal rendelkezik.
     */
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
        if(honey.size() > 0 && isAlive()) {
            Tools h = getHoney();
            field.setTools(h);
            h.setField(this.field);
        }
    }
    /**
     * Ezzel rakjuk le a megfelelo mezore az Olajat
     */
    public void throwOil(){
        if(oil.size() > 0 && isAlive()) {
            Tools h=getOil();
            field.setTools(h);
            h.setField(this.field);
        }

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
    public String getIGName() { return IGName;}
    /**
     * Ha meghal a játékos(pl: lyukba lép) akkor hívódik meg.
     */
    public void die() {

            alive = false;
            ArrayList<Worker> players = getField().getWarehouse().getPlayerList();
            int count = 0;
            for(int i = 0; i < players.size(); i++) {
                if(!players.get(i).isAlive()) {
                    count++;
                }
                if(players.size() == count) {
                    getField().getWarehouse().getGame().endGame();
                }
            }
    }


    /**
     *
     * @param point A játékos pontszámának megváltoztatása a legfőbb feladata a függvénynek.
     */
    public void addPoints(int point) {
        this.point += point;
        if(this.point == getField().getWarehouse().getMaxPoints()) {
            this.win();
        }
    }

    /**
     * Minden játékos rendelkezik a feladás lehetőségével,
     * mikor élni kíván vele egy játékos ez a metódus hívódik meg.
     */
    public void surrender(Player player) {
        player.win();
    }

    /**
     * A függvény meghívódik ha az egyik játékos nyert, tehát az összes saját területén láda áll.
     */
    public void win() {
        Main.winner(this.getIGName() + " WON!");
        System.exit(0);
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

    public String getDescription() {
return"";
            }

}
