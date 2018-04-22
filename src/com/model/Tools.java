package com.model;

/**
 * Eszkozok reprezentalo osztaly, amit ha a jatekos ledob, valtoztathatja a mezo surlodasat, ezaltal akadalyozva
 * tobb lada eltolasat.
 */
public class Tools {

    /**
     * A megvaltoztatott surlodas.
     */
    private int changeFriction;

    /**
     * A surlodas lekerdezese
     * @return surlodas
     */
    public int getChangeFriction() {
        return changeFriction;
    }

    /**
     * A surlodas beallitasa
     * @param changeFriction a beallitando surlodas
     */
    public void setChangeFriction(int changeFriction) {
        this.changeFriction = changeFriction;
    }

    /**
     * A tool-t tartalmazo mezo.
     */
    private Field field;

    /**
     * A tool-t tartalmazo mezo lekerdezese
     * @return a tool-t tartalmazo mezo
     */
    public Field getField() {
        return field;
    }

    /**
     * A tool-t tartalmazo mezo lekerdezese
     * @param field a tool-t tartalmazo mezo
     */
    public void setField(Field field) {
        this.field = field;
    }

    public void getDescription() {

    }

}
