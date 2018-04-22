package com.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A játékban magát a pályát képviseli, annak tulajdonságaival, illetve metódusaival.
 *
 */
public class Warehouse {

    /**
     * A játékban szereplő, még tolható ládák száma.
     */
    private int pushableBoxes = 10;

    /**
     * A pályán található mezőket tárolja(Field,Switch,TrapDoor,Wall vagy Hole)
     */
    private List<Field> fields;


    public List<Switch> sw=new ArrayList<>();
    public List<TrapDoor>tr=new ArrayList<>();

    /**
     * A játékosokat tároló lista.
     */
    private List<Player> playerList;

    /**
     * Létrehozza a pályán lévő mezőket, játékosokat, tulajdonképpen előkészíti a játék kezdetét.
     */
    public void initialize(String file) throws IOException {

        int row=0;
        FileReader fr = new FileReader(file); // itt persze majd azt a mapot olvassa be amit éppen akar(3közül valamelyik. MEGOLDVA
        BufferedReader br=new BufferedReader(fr);;
        String line;
        int sizeRow=0;
        int sizeColumn=0;
        while((line=br.readLine())!=null){
            String[]a=line.split(" ");
            sizeColumn=a.length;
            sizeRow++;
        }

        String [][]palya=new String[sizeRow][sizeColumn];
        line="";

        while((line=br.readLine())!=null){

            String[]a=line.split(" ");
            for (int i = 0; i <a.length ; i++) {
                palya[row][i]=a[i];
            }
            for (int i = 1; i <a.length ; i++) {
                Field f0=  melyik_elem(a[i]);
                if(row==0) {
                    Field field1 = melyik_elem(palya[row][i - 1]);

                    field1.setNeighbors(Direction.THIRD, f0);//szomszédok beállítása
                    f0.setNeighbors(Direction.FIRST, f0);

                    field1.setNeighbors(Direction.SECOND,null);
                    f0.setNeighbors(Direction.SECOND,null);
                    if(i==1){
                        field1.setNeighbors(Direction.FIRST,null);

                    }
                    else if(i==a.length-1){
                        f0.setNeighbors(Direction.FIRST,null);

                    }

                }
                else{
                    Field field1 = melyik_elem(palya[row][i - 1]);

                    field1.setNeighbors(Direction.THIRD, f0);//szomszédok beállítása
                    f0.setNeighbors(Direction.FIRST, f0);

                    Field f02=melyik_elem(palya[row-1][i]);
                    Field field12=melyik_elem(palya[row-1][i-1]);

                    field1.setNeighbors(Direction.SECOND,field12);//lenti szomszéd, fenti szomszéd
                    f0.setNeighbors(Direction.SECOND,f02);

                    f02.setNeighbors(Direction.FOURTH,f0);
                    field12.setNeighbors(Direction.FOURTH,field1);


                    if(i==1){
                        field1.setNeighbors(Direction.FIRST,null);

                    }
                    else if(i==a.length-1){
                        f0.setNeighbors(Direction.FIRST,null);

                    }

                }


                }
            row++;
            }

        boolean b=false;
        if(sw.size()<tr.size()){
            for (int i = 0; i <sw.size() ; i++) {
                if(i==sw.size()-1){
                    for (int j = 0; j <tr.size() ; j++) {
                        sw.get(i).;
                        tr.get(i);
                    }
                }
                else{
                    sw.get(i);
                    tr.get(i);
                }

            }


        }



        }









        /*System.out.println("Munkas beallitasa elso mezore: ");
        this.fields = new ArrayList<>();
        this.playerList = new ArrayList<>();
        Field playerField = new Field();
        createPlayer();
        createPlayer();
        playerField.setElement(playerList.get(0));
        this.fields.add(playerField);
        playerList.get(0).setField(playerField);*/



    public Field melyik_elem(String a) {
        Field f=new Field();
        switch (a) {

            case "#":
                Field field1 = new Field();
                Wall wall = new Wall();
                field1.setElement(wall);
                f=field1;
                addField(field1);
                break;
            case ".":
                Field field=new Field();
                f=field;
                addField(field);
                break;


            case "1":
                Field field2 = new Field();
                Worker player1 = new Worker();
                field2.setElement(player1);
                createPlayer(player1);
                f=field2;
                addField(field2);
                break;

            case "2":
                Field field3 = new Field();
                Worker player2 = new Worker();
                field3.setElement(player2);
                createPlayer(player2);
                f=field3;
                addField(field3);
                break;
            case "%":
                Field field4 = new Field();
                Column column = new Column();
                field4.setElement(column);
                f=field4;
                addField(field4);
                break;
            case "O":
                Hole hole = new Hole();
                addField(hole);
                f=hole;
                break;
            case "X":
                Field field5 = new Field();
                Box box = new Box();
                field5.setElement(box);
                f=field5;
                addField(field5);
                break;
            case "S":
                Switch s = new Switch();
                f=s;
                sw.add(s);
                addField(s);

                break;
            case "H":
                Honey honey = new Honey();
                Field field6 = new Field();
                field6.setTools(honey);
                f=field6;
                addField(field6);
                break;
            case "L":
                Oil oil = new Oil();
                Field field7 = new Field();
                field7.setTools(oil);
                addField(field7);
                f=field7;
                break;
            case "T":
                TargetField target = new TargetField();
                f=target;
                addField(target);
                break;
            case "!":
                TrapDoor trap = new TrapDoor();
                f=trap;
                tr.add(trap);
                addField(trap);
                break;
            case "F":
                TargetField tar = new TargetField();
                f=tar;
                addField(tar);
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

    public int getPushableBoxes() {
        return pushableBoxes;
    }

    /**
     * A metódus létrehoz egy új Workert, akit egyből hozzá is ad a játékosokat tartalmazó listához.
     */
    public void createPlayer(Worker worker) // kis javítás, paraméterül kapja meg
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
        System.out.println("-->[Warehouse].setPushableBoxes(num)");
        pushableBoxes -= num;
        System.out.println("<--");
    }

    /**
     * Megadja a játékosok listáját
     * @return játékosokat tartalmazó lista
     */
    public List<Player> getPlayerList() {
        return playerList;
    }
}
