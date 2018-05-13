package com.model;


import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 * A játékban magát a pályát képviseli, annak tulajdonságaival, illetve metódusaival.
 *
 */
public class Warehouse {

    /**
     * A játékban szereplő, még tolható ládák száma.
     */
    private int pushableBoxes = 0;

    /**
     * A pályán található mezőket tárolja(Field,Switch,TrapDoor,Wall vagy Hole)
     */
    private ArrayList<Field> fields = new ArrayList<Field>();

    /**
     * A két játékos
     */
    Worker player_1 = new Worker();
    Worker player_2 = new Worker();

    /**
     * A kapcsolókat tartalmazó lista
     */
    public List<Switch> sw=new ArrayList<>();

    /**
     * A csapóajtókat tartalamzó lista
     */
    public List<TrapDoor>tr=new ArrayList<>();

    /**
     * A játékosokat tároló lista.
     */
    private ArrayList<Worker> playerList = new ArrayList<>();

    /**
     * A célmezőket tartalmazó lista
     */
    private ArrayList<TargetField> targetFields = new ArrayList<>();

    /**
     * A tolható objektumokat tartalmazó lista
     */
    private ArrayList<Pushable> pushables = new ArrayList<>();

    /**
     * A tolható objektumokat kérdezi le
     * @return még tolható objektumok listája
     */
    public ArrayList<Pushable> getPushables() {
        return pushables;
    }

    /**
     * A tolható objektumok listáját bővítő függvény
     * @param pushable tolható objektum
     */
    public void addPushable(Pushable pushable) {
        pushables.add(pushable);
    }

    /**
     * Törli az átadott tolható objektumot a listából
     * @param pushable tolható objektum
     */
    public void removePushable(Pushable pushable) {
        pushables.remove(pushable);
    }

    /**
     * A játék pályáját tároló 2D-s tömb
     */
    private Field[][] map;

    /**
     * Lekérdezi a játék pályáját
     * @return játék pályája
     */
    public Field[][] getMap() {
        return map;
    }

    /**
     * segédváltozó a játék pályájának méretére
     */
    private int sizeRow = 0;

    /**
     * segédváltozó a játék pályájának méretére
     */
    private int sizeColumn=0;

    /**
     * Lekérdezi a pálya sorainak számát
     * @return sorok száma
     */
    public int getSizeRow() {
        return sizeRow;
    }

    /**
     * Lekérdezi a pálya oszlopainak számát
     * @return oszlopok száma
     */
    public int getSizeColumn() {
        return sizeColumn;
    }

    /**
     * A játékok kezelő változó
     */
    public Game game;

    /**
     *
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * a játékban szerezhető maximlis pont
     */
    private int maxPoints = 0;

    /**
     * Lekérdezi a játékban szerezhető maximális pontot
     * @return elérhető maximális pontszám
     */
    public int getMaxPoints() {
        return maxPoints;
    }

    /**
     * Beállítja a maximálisan szerezhető pontok számát
     * @param points
     */
    public void setMaxPoints(int points) {
        maxPoints += points;
    }
    /**
     * Beolvassa a pálya txt fájlt, majd inicializálja a pályát belőle
     * @param file A beolvasandó pálya fájlának neve
     * @throws IOException
     */
    public void initialize(String file) throws IOException {

        int row=0;
       // File f = new File(file);
        FileReader fr = new FileReader(file); // itt persze majd azt a mapot olvassa be amit éppen akar(3közül valamelyik. MEGOLDVA
        //System.out.println(f.getAbsolutePath());
        BufferedReader br=new BufferedReader(fr);;
        String line;


        /**
         * Első beolvasánál a pálya méretét inicializálja
         */
        while((line=br.readLine())!=null){
            String[] a=line.split(" ");
            sizeColumn=a.length;
            sizeRow++;
        }

        map=new Field[sizeRow][sizeColumn];
        line="";

        /**
         * A következő beolvasásánál pedig feltölti a 2D-s mátrixot
         */
        br = new BufferedReader(new FileReader(file));
        while((line=br.readLine())!=null) {

            String[] a = line.split(" ");
            for (int i = 0; i < a.length; i++) {
                map[row][i] = spawn_element(a[i]);
                System.out.print(a[i]);
            }

            row++;
            System.out.print("\n");

        }

        /**
         * Beállítja a mezők szomszédjait, külön kezelve első és utolsó sor/oszlop szerint
         */
        for(int i = 0; i < sizeRow; i++) {
            for (int j = 0; j < sizeColumn; j++) {
                Field spawn = map[i][j];

                //első sor
                if(i == 0) {

                    //első oszlop
                    if(j == 0) {
                        spawn.setNeighbors(Direction.THIRD, map[i][j + 1]);

                        //utolsó oszlop
                    } else if (j == sizeColumn - 1) {
                        spawn.setNeighbors(Direction.FIRST, map[i][j - 1]);

                        //közép
                    } else {
                        spawn.setNeighbors(Direction.FIRST, map[i][j - 1]);
                        spawn.setNeighbors(Direction.THIRD, map[i][j + 1]);
                    }

                    spawn.setNeighbors(Direction.FOURTH, map[i+1][j]);

                    //utolsó sor
                } else if (i == sizeRow - 1) {

                    //első oszlop
                    if(j == 0) {
                        spawn.setNeighbors(Direction.THIRD, map[i][j + 1]);

                        //utolsó oszlop
                    } else if (j == sizeColumn - 1) {
                        spawn.setNeighbors(Direction.FIRST, map[i][j - 1]);

                        //közép
                    } else {
                        spawn.setNeighbors(Direction.FIRST, map[i][j - 1]);
                        spawn.setNeighbors(Direction.THIRD, map[i][j + 1]);
                    }

                    spawn.setNeighbors(Direction.SECOND, map[i-1][j]);

                    //közép
                } else {

                    //első oszlop
                    if(j == 0) {
                        spawn.setNeighbors(Direction.THIRD, map[i][j + 1]);

                        //utolsó oszlop
                    } else if (j == sizeColumn - 1) {
                        spawn.setNeighbors(Direction.FIRST, map[i][j - 1]);

                        //közép
                    } else {
                        spawn.setNeighbors(Direction.FIRST, map[i][j - 1]);
                        spawn.setNeighbors(Direction.THIRD, map[i][j + 1]);
                    }

                    spawn.setNeighbors(Direction.SECOND, map[i-1][j]);
                    spawn.setNeighbors(Direction.FOURTH, map[i+1][j]);
                }
            }
        }

        /**
         * A switch mezők összepárosítása a trapdoor mezőkkel.
         */
        boolean b=false;
        if(sw.size()<=tr.size()){
            for (int i = 0; i <sw.size() ; i++) {
                if(i == sw.size()-1 ){
                    for (int j = i; j <tr.size() ; j++) {
                        sw.get(i).setTrapDoor(tr.get(j));

                    }
                }
                else{
                    sw.get(i).setTrapDoor(tr.get(i));

                }

            }


        }


        br.close();
        }


    /**
     * Eldönti beolvasás során, hogy egy karakter milyen elemnek felel meg, azt inicializálja és beállítja a megfelelő tulajdonságokat.
     * A karakterekhez tartozó elemek és mezők listája a dokumentációban megtalálható
     * @param a átvett karakter
     * @return a létrehozott mező
     */
    public Field spawn_element(String a) {
        Field f=new Field();
        switch (a) {
            case "#":
                Field field1 = new Field();
                Wall wall = new Wall();
                field1.setElement(wall);
                f=field1;
                field1.setWarehouse(this);
                addField(field1);
                break;

            case ".":
                Field field=new Field();
                f=field;
                field.setWarehouse(this);
                addField(field);

                break;

            case "1":
                Field field2 = new Field();

                //vegso megoldasnal majd adhatunk custom nevet is akar
                player_1.setName("1");
                player_1.setField(field2);
                field2.setElement(player_1);
                playerList.add(player_1);

                f=field2;
                field2.setWarehouse(this);
                addField(field2);

                break;

            case "2":
                Field field3 = new Field();

                //vegso megoldasnal majd adhatunk custom nevet is akar
                player_2.setName("2");
                player_2.setField(field3);
                field3.setElement(player_2);
                playerList.add(player_2);

                f=field3;
                field3.setWarehouse(this);
                addField(field3);

                break;
            case "%":
                Field field4 = new Field();
                Column column = new Column();
                field4.setElement(column);
                f=field4;
                field4.setWarehouse(this);
                addField(field4);

                break;
            case "O":
                Hole hole = new Hole();
                hole.setWarehouse(this);
                addField(hole);
                f = hole;

                break;
            case "X":
                Field field5 = new Field();
                Box box = new Box();
                setPushableBoxes(1);
                box.setField(field5);
                field5.setElement(box);
                f=field5;
                field5.setWarehouse(this);
                addField(field5);
                addPushable(box);
                break;

            case "S":
                Switch s = new Switch();
                f = s;
                sw.add(s);
                s.setWarehouse(this);
                addField(s);

                break;
            case "H":
                Honey honey = new Honey();
                Field field6 = new Field();
                field6.setTools(honey);
                f=field6;
                field6.setWarehouse(this);
                addField(field6);

                break;
            case "L":
                Oil oil = new Oil();
                Field field7 = new Field();
                field7.setTools(oil);
                field7.setWarehouse(this);
                addField(field7);
                f=field7;

                break;
            case "T":
                TargetField target_1 = new TargetField();
                targetFields.add(target_1);
                this.setMaxPoints(10);
                f=target_1;
                target_1.setPlayer(player_1);
                target_1.setWarehouse(this);
                addField(target_1);

                break;

            case "F":
                TargetField target_2 = new TargetField();
                targetFields.add(target_2);
                f=target_2;
                target_2.setPlayer(player_2);
                target_2.setWarehouse(this);
                addField(target_2);

                break;

            case "!":
                TrapDoor trap = new TrapDoor();
                f=trap;
                tr.add(trap);
                trap.setWarehouse(this);
                addField(trap);
                break;
            case "?":
                TrapDoor closed = new TrapDoor();
                f=closed;
                tr.add(closed);
                closed.setWarehouse(this);
                addField(closed);
                break;
        }
        return f;
    }



    /**
     * Hozzáadja az átvett objektumot a mezőket tartalmazó listához.
     * @param field Field típusú objektum
     *             
     */
    public void addField(Field field) {
        this.fields.add(field);
    }

    /**
     * A meg tolhato objektumok számának lekérdezése
     * @return A megmaradt tolható objektumok
     */
    public int getPushableBoxes() {
        return pushableBoxes;
    }

    /**
     * A metódus létrehoz egy új Workert, akit egyből hozzá is ad a játékosokat tartalmazó listához.
     */
    public void createPlayer(Worker worker)
    {
        playerList.add(worker);
    }

    /**
     * Eltávolítja a játékost a pályáról
     * @param player játékos
     */
    public void removePlayer(Player player) { playerList.remove(player);}

    /**
     * A metódus fő célja, hogy a még tolható ládák számát folyamtosan állítani tudja,
     * hiszen ha már ez a szám 0 akkor vége a játéknak.
     * @param num Egy integer, ami a még tolható ládák számát adja meg.
     *           
     */
    public void setPushableBoxes(int num) {
        pushableBoxes += num;
        System.out.println("TOLHATO LADAK SZAMA: " + pushableBoxes);
        if(pushableBoxes == 0) {
            getGame().endGame();
        }
    }

    /**
     * Megadja a játékosok listáját
     * @return játékosokat tartalmazó lista
     */
    public ArrayList<Worker> getPlayerList() {
        return playerList;
    }
}
