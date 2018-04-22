package com.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A pályán lévő mezőket reprezentáló osztály
 *
 * A mező ismeri szomszédos mezőit négy irányban, a rajta álló elemet.
 */
public class Field {

    /**
     * Ez a surlodasi tenyezo amit
     */
    private int friction=1;

    public void setFriction(int s)
    {
        this.friction = s;
    }
    public int getFriction(){
        return friction;
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

    public Tools getTools() {
        return tools;
    }

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
        return warehouse;
    }

    /**
     * Megadja a mezőn jelenleg álló elemet
     * @return mezőn álló elem
     */
    public Element getElement() {
       return this.element;
    }

    /**
     * Beállítja a hozzátartozó elemet
     * @param element a hozzátartozó elem
     */
    public void setElement(Element element) {
        this.element = element;
    }

    public Field() {

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
    public void removeElement(Player player) { }

    /**
     * Eltávolítja a rajta álló tolható elemet
     * @param pushable a mezőn álló tolható objektum
     */
    public void removeElement(Pushable pushable) { }


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
        fieldMap.put(direction,field);
    }

    /**
     * A mezőre elem lépett
     * @param element a mezőre lépett elem
     */
    public void stepOnIt(Element element) {

    }

    /**
     * A mezőre tolható elem lépett
     * @param pushable a mezőre lépett tolható elem
     */
    public void stepOnIt(Pushable pushable) {

    }

    /**
     * A mezőre játékos lépett
     * @param player a mezőre lépett játékos
     */
    public void stepOnIt(Player player) {

    }



}
