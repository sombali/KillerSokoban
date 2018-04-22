package com.model;

public abstract class Tools {
    /**
     * A tool-t tartalmazo mezo.
     */
    private Field field;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public abstract int getChangeFriction();

    public  void drop(Field field){} ; // ez csak grafikusnal fog kelleni szerintem
}
