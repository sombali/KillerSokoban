package com.model;

/**
 * Mez osztaly, egy mezore dobva noveli annak surlodasat, igy lehetseges,
 * hogy a jatekos nem tudja tovabbtolni a ladat/ladakat errol a mezorol
 */
public class Honey extends Tools {

    /**
     * Mez konstruktor, a mezőnek növeli a surlódását 5el.
     */

    public Honey() {
        setChangeFriction(6);
    }



    public HoneyView hv = new HoneyView();

    public Drawable getView(){
        return  hv;
    }
    public void getDescription() {
        System.out.print("h");
    }

}
