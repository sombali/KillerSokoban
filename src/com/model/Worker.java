package com.model;

/**
 * A Worker típusú játékosok metódusait foglalja magában ez az osztály.
 */
public class Worker extends Player {

    /**
     * A függvény felelős a Worker megfelelő irányba történő léptetéséért.
     * Megnézi, hogy melyik a követekző mező és azon áll e valamilyen element és ennek függvényében
     * hajtja végre a mozgást.
     * @param direction Egy paraméterül átvett irány,amelyikbe lépni kíván a Worker.
     *
     */
    @Override
    public void move(Direction direction) {

        super.move(direction);

        if(this.isAlive()) {


            Field nextfield =  getField().getNeighbors(direction);
            Element nextElement = nextfield.getElement();

            boolean allow;
            if(nextElement != null) {
                allow =  nextElement.hit(this, direction, getStrength());
                if(allow==true){
                    step(nextfield);
                }
            } else {
                step(nextfield);
            }

        }


    }

    public void getDescription() {
        System.out.print(this.getName());
    }
}
