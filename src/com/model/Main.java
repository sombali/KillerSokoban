package com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Main osztály a main metódus megvalósításáért felelős.
 */
public class Main {
    /**
     *
     * @param args
     * A program main függvénye, itt történnek a játék során létrejövő objektumok példányosításai, függvényhívásai,
     * illetve egyéb műveletei.
     */
    public static void main(String[] args) throws IOException {

        Game game = new Game();


        //Egyszeru menu
        //Scanner in = new Scanner(System.in); //Csak beolvas
        /*
        System.out.println("Jatek Lepeget - Irj 1-et, majd nyomj egy Entert");
        System.out.println("Jatek Feladasa - Irj 2-ot, majd nyomj egy Entert");
        System.out.println("Jatek Megnyerese - Irj 3-at, majd nyomj egy Entert");
        int x = in.nextInt();
        Player testPlayer = game.getWarehouse().getPlayerList().get(0);
        if(x==1) {
            //testPlayer.move(Direction.FIRST);
            testPlayer.move(Direction.FIRST);
        } else if(x == 2) {
            testPlayer.surrender();
        } else if(x == 3) {
            testPlayer.win();
        }*/


        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        List<String> stringList = new ArrayList<>();
        String command;

        while (!(command = br.readLine()).trim().equals("")) {
            stringList.add(command);
        }




        // ezt kicsit később kéne. a 66. sorban állítom be a pályát csak. azt azért mert át kell vennie paraméterként hogy melyik mapot allítsa és azt ott írtad meg.
        //Ezért át kéne oda rakni,mert így hibát dob a kövi sorokban
        Player player1 = game.getWarehouse().getPlayerList().get(0);
        Player player2 = game.getWarehouse().getPlayerList().get(1);


       for(int i = 0; i < stringList.size(); i++) {
            String[] splittedString = stringList.get(i).split(" ");
                if(splittedString[0].equals("loadmap")) {
                    //TODO
                   // game.warehouse.initialize(splittedString[1]);
                    game.startGame(splittedString[1]);// feltölti a pályát, beállítja a szomszédokat. A splittedString[1] a pályát tartalmazó txt lesz igaz?



                } else if(splittedString[0].equals("stepplayer")) {
                    if(splittedString[1].equals("1")) {
                        if(splittedString[2].equals("1")) {
                            player1.move(Direction.FIRST);
                        } else if(splittedString[2].equals("2")) {
                            player1.move(Direction.SECOND);
                        } else if(splittedString[2].equals("3")) {
                            player1.move(Direction.THIRD);
                        } else if(splittedString[2].equals("4")) {
                            player1.move(Direction.FOURTH);
                        }
                    } else if(splittedString[1].equals("2")) {
                        if(splittedString[2].equals("1")) {
                            player2.move(Direction.FIRST);
                        } else if(splittedString[2].equals("2")) {
                            player2.move(Direction.SECOND);
                        } else if(splittedString[2].equals("3")) {
                            player2.move(Direction.THIRD);
                        } else if(splittedString[2].equals("4")) {
                            player2.move(Direction.FOURTH);
                        }
                    }
                } else if(splittedString[0].equals("surrend")) {
                    if(splittedString[1].equals("1")) {
                        player1.surrender();
                    } else if(splittedString[1].equals("2")) {
                        player2.surrender();
                    }
                } else if(splittedString[0].equals("droptool")) {
                    if(splittedString[1].equals("1")) {
                        if(splittedString[2].equals("honey")) {
                            player1.getHoney().drop(player1.getField());
                        } else if(splittedString[2].equals("oil")) {
                            player1.getOil().drop(player1.getField());
                        }
                    } else if(splittedString[1].equals("2")) {
                        if(splittedString[2].equals("honey")) {
                            player2.getHoney().drop(player2.getField());
                        } else if(splittedString[2].equals("oil")) {
                            player2.getOil().drop(player2.getField());
                        }
                    }
                } else if(splittedString[0].equals("listplayers")) {
                    List<Player> players = game.getWarehouse().getPlayerList();
                    for(int j = 0; j < players.size(); j++) {
                        System.out.println(players.get(j).getName() + " " + "ALIVE EZT NEM TUDOM" +
                         " " + players.get(j).getPoint() + " " + players.get(j).getHoneyList().size() + " " +
                         players.get(j).getOilList().size());
                    } //TODO azert ir ki null-t mert nincs beallitva nev (szerintem)
                } else if(splittedString[0].equals("printpushableboxes")) {
                    System.out.println("Pushableboxes: " + game.getWarehouse().getPushableBoxes());
                } else if(splittedString[0].equals("save")) {
                    //TODO
                } else if(splittedString[0].equals("load")) {
                    //TODO
                } else if(splittedString[0].equals("listtargetfields")) {
                    //TODO
                }
        }

    }
}
