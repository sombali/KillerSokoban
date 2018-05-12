package com.model;

/**
 * Olaj osztaly, egy mezore dobva csokkenti annak surlodasat, mezre dobva kiuti annak hatasat
 */
public class Oil extends Tools {

    /**
     * Olaj konstruktor, a mezőnek növeli a surlódását 5el.
     */
    public Oil() {
        setChangeFriction(-2);
    }

    public OilView ov = new OilView();

    public Drawable getView(){
        return  ov;
    }

    public void getDescription() {
        System.out.print("L");
    }



}
