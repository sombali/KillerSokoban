package com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        List<String> stringList = new ArrayList<>();
        String command;
        Player player1 = null;
        Player player2 = null;
        while ((command = br.readLine())!=null) {

           if(command.isEmpty())
               break;
           String[] splittedString = command.split(" ");
           if(splittedString[0].equals("loadmap")) {
               game.startGame(splittedString[0]);
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
                    player1.throwHoney();
                } else if(splittedString[2].equals("oil")) {
                    player1.throwOil();
                }
            } else if(splittedString[1].equals("2")) {
                if(splittedString[2].equals("honey")) {
                    player2.throwHoney();
                } else if(splittedString[2].equals("oil")) {
                    player2.throwOil();
                }
            }
        } else if(splittedString[0].equals("listplayers")) {
            List<Worker> players = game.getWarehouse().getPlayerList();
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
        }else if (splittedString[0].equals("seeresult")){
            //TODO ide kell irni azt a cuccot ami kirajzolja az eredmeny palyat
        } else {
            System.out.println("Invalid parancs, írd be újból");
        }
        }

    }
}
