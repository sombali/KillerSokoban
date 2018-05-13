package com.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A pályán lévő mezőket reprezentáló osztály
 *
 * A mező ismeri szomszédos mezőit négy irányban, a rajta álló elemet.
 */
public class Field {

    private FieldView fv = new FieldView();

    public Drawable getView() {
        return fv;
    }
    /**
     * A mezőn álló elem
     */
    private Element element;

    /**
     * Egy szomszédo mező iránya
     */
    Direction direction;

    /**
     * A szomszédos mezők, irány szerint tárolva
     */
    private Map<Direction, Field> fieldMap = new HashMap<>();

    /**
     * A mezon levo Tool.
     */
    private Tools tools;

    /**
     * A mezőn lévő toolok lekérdezése
     * @return a mezőn lévő toolok
     */
    public Tools getTools() {
        return tools;
    }

    /**
     * A mezőn lévő toolok beállítása
     * @param tools
     */
    public void setTools(Tools tools) {
        this.tools = tools;
    }

    /**
     * A mezőt tartalmazó pálya
     */
    private Warehouse warehouse;

    /**
     * Beállítja a kívánt pályát a mezőnek
     * @param warehouse a mezőt tartalmazó pálya
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Megadja a mezőhöz tartozó pályát
     * @return mezőhöz tartozó pálya
     */
    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    /**
     * Megadja a mezőn jelenleg álló elemet
     * @return mezőn álló elem
     */
    public Element getElement() {
        return element;
    }

    /**
     * Beállítja a hozzátartozó elemet
     * @param element a hozzátartozó elem
     */
    public void setElement(Element element) {
        this.element = element;
    }

    /**
     * A mezőre elem lép, beállítja a saját elemének, az elem mezőjének pedig beállítja saját magát
     * @param element a mezőre lépni kívánó elem
     */
    public void acceptElement(Element element) {
        element.setField(this);
        setElement(element);
    }
    
    /**
     * Eltávolítja magáról a rajta álló játékost
     * @param player a mezőn álló játékos
     */
    public void removeElement(Element player) {
        this.element = null;
    }

    /**
     * Eltávolítja a rajta álló tolható elemet
     * @param pushable a mezőn álló tolható objektum
     */
    public void removeElement(Pushable pushable) {
        this.element = null;
    }


    /**
     * Megadja a szomszédos mezőket a kívánt irányban
     * @param direction a kívánt irány
     * @return szomszédos mező
     */
    public Field getNeighbors(Direction direction) {
        return fieldMap.get(direction);
    }

    /**
     * Beállítja a szomszédos mezőket a kívánt irányban
     * @param direction a szomszédos mező iránya
     * @param field a szomszédos mező
     */
    public void setNeighbors(Direction direction, Field field) {
        fieldMap.put(direction, field);
    }

    /**
     * A mezőre elem lépett
     * @param element a mezőre lépett elem
     */
    public void stepOnIt(Element element) {

        removeElement(element);
        if(element.getDescription().equals("1")){
            warehouse.player_1.die();
            warehouse.game.endGame();
        }
        if(element.getDescription().equals("2")){
            warehouse.player_2.die();
            warehouse.game.endGame();
        }

        //TODO
    }

    /**
     * A mezőre tolható elem lépett
     * @param pushable a mezőre lépett tolható elem
     */
    public void stepOnIt(Pushable pushable) {
        //TODO
    }

    /**
     * A mezőre játékos lépett
     * @param player a mezőre lépett játékos
     */
    public void stepOnIt(Player player) {
        //TODO
    }


    public void getDescription() {
        System.out.print(".");
    }


}
