package com.model;

public class Oil extends Tools {
    /**
     * A megvaltoztatott surlodas.
     */
    private int changeFriction=-2;

    public  int getChangeFriction() {
        return changeFriction;
    }

    public void setChangeFriction(int changeFriction) {
        this.changeFriction = changeFriction;
    }
}
