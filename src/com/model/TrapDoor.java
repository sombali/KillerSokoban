package com.model;

/**
 * Ez a játékban szereplő csapóajtót reprezentáló osztály.
 */
public class TrapDoor extends Field {

    /**
     * Switch A TrapDoorhoz tartozó, őt nyitó/záró kapcsoló(Switch).
     */
    private Switch aSwitch;

    /**
     * A TrapDoor éppeni állatopát tárolja.
     */
    private TrapDoorState state = TrapDoorState.CLOSED;

    /**
     * Ezt az objektum mezőről való eltávolítását végző a metódus.
     * @param pushable a mezőn álló tolható objektum
     *  
     */
    @Override
    public void removeElement(Pushable pushable) {
        setElement(null);
    }
    

    /**
     * Eltávolítja a rajta álló játékost
     * @param player mezőn álló játékos
     */
    @Override
    public void removeElement(Player player) {
        setElement(null);
    }
    /**
     * Ez a függvény fogja kiváltani azt a hatást ami történik mikor rálép egy játékos.
     * Ha a Trapdoor nyitva van a játékos meghal, ha nem akkor szimpla mezőként funkcionál.
     * @param player játékos, ami a mezőre lépett
     * 
     */
    @Override
    public void stepOnIt(Player player) {
        if(state.equals(TrapDoorState.OPENED)) {
            removeElement(player);
            player.die();
        } else {
            super.stepOnIt(player);
        }
    }

    /**
     * Ez a függvény fogja kiváltani azt a hatást ami akkor történik mikor rálép egy tolható objektum.
     * Ha a Trapdoor nyitva van a játékos meghal, ha nem akkor szimpla mezőként funkcionál.
     * @param pushable objektum, ami a mezőre lépett
     *
     */
    @Override
    public void stepOnIt(Pushable pushable) {
        if(state.equals(TrapDoorState.OPENED)) {
            removeElement(pushable);
            getWarehouse().setPushableBoxes(-1);
        } else {
            super.stepOnIt(pushable);
    }
    }

    /**
     * 
     * @return az Element ami a TrapDooron áll.
     */
    @Override
    public Element getElement() {
        if(state.equals(TrapDoorState.CLOSED)) {
            return super.getElement();
        } else {
            return null;
        }
    }

    /**
     * Ez a függvény felelős azért, hogy a Trapdoor állapotát (nyitott vagy zárt) változtassa.
     */
    public void switchState() {
        if(state.equals(TrapDoorState.CLOSED)) {
            state = TrapDoorState.OPENED;
        } else {
            state = TrapDoorState.CLOSED;
        }
    }
    public void setaSwitch(Switch s){
        aSwitch=s;
    }
    public  Switch getaSwitch(){
        return aSwitch;
    }

    public void getDescription() {
        if(this.state.equals(TrapDoorState.CLOSED)) {
            System.out.print("?");
        } else {
            System.out.print("!");
        }
    }
}
