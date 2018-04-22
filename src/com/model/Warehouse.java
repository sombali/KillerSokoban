package com.model;


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

    private Field[][] map;

    public Field[][] getMap() {
        return map;
    }

    private int sizeRow = 0;

    private int sizeColumn=0;

    public int getSizeRow() {
        return sizeRow;
    }

    public int getSizeColumn() {
        return sizeColumn;
    }

    /**
     * Beolvassa a pálya txt fájlt, majd inicializálja a pályát belőle
     * @param file A beolvasandó pálya fájlának neve
     * @throws IOException
     */
    public void initialize(String file) throws IOException {

        int row=0;
        File f = new File(file);
        FileReader fr = new FileReader(file); // itt persze majd azt a mapot olvassa be amit éppen akar(3közül valamelyik. MEGOLDVA
        System.out.println(f.getAbsolutePath());
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
        while((line=br.readLine())!=null){

            String[] a = line.split(" ");
            for (int i = 0; i <a.length ; i++) {
                map[row][i]=melyik_elem(a[i]);
                System.out.print(a[i]);
            }

            row++;

            System.out.print("\n");

            /*
            for (int i = 1; i <a.length ; i++) {
                Field f0 =  melyik_elem(a[i]);
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
            row++;*/
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
         * A switch mezők összepűrosítása a trapdoor mezőkkel.
         */
        boolean b=false;
        if(sw.size()<tr.size()){
            for (int i = 0; i <sw.size() ; i++) {
                if(i == sw.size() - 1){
                    for (int j = i; j <tr.size() ; j++) {
                        sw.get(i).setTrapDoor(tr.get(j));

                    }
                }
                else{
                    sw.get(i).setTrapDoor(tr.get(i));

                }

            }


        }



        }

    /**
     * Eldönti beolvasás során, hogy egy karakter milyen elemnek felel meg, azt inicializálja és beállítja a megfelelő tulajdonságokat.
     * A karakterekhez tartozó elemek és mezők listája a dokumentációban megtalálható
     * @param a átvett karakter
     * @return
     */
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

                //vegso megoldasnal majd adhatunk custom nevet is akar
                player_1.setName("1");
                player_1.setField(field2);
                field2.setElement(player_1);
                playerList.add(player_1);

                f=field2;
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
                f = hole;
                return hole;
            case "X":
                Field field5 = new Field();
                Box box = new Box();
                setPushableBoxes(1);
                field5.setElement(box);
                f=field5;
                addField(field5);
                break;
            case "S":
                Switch s = new Switch();
                f = s;
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
                TargetField target_1 = new TargetField();
                targetFields.add(target_1);
                f=target_1;
                target_1.setPlayer(player_1);
                addField(target_1);
                break;

            case "F":
                TargetField target_2 = new TargetField();
                targetFields.add(target_2);
                f=target_2;
                target_2.setPlayer(player_2);
                addField(target_2);
                break;

            case "!":
                TrapDoor trap = new TrapDoor();
                f=trap;
                tr.add(trap);
                addField(trap);
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
        if(pushableBoxes == 0);
    }

    /**
     * Megadja a játékosok listáját
     * @return játékosokat tartalmazó lista
     */
    public ArrayList<Worker> getPlayerList() {
        return playerList;
    }
}
