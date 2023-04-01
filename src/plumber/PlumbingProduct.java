package plumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PlumbingProduct {

    private Set<Direction> ends = new HashSet<>();
    private Cell cell;

    // ------------------ Конструктор ----------------------------------------------


    public PlumbingProduct(Set<Direction> ends, Cell cell){

        if (cell == null || ends.size() == 0){
            throw new IllegalArgumentException("Illegal argument for PlumbingProduct");
        }

        this.ends = new HashSet<Direction>(ends);
        this.cell = cell;
    }


    // --------------------- взаимодействие с водой ----------------------------------

    private Water water = null;
    public void  fill(){

    }

    public  boolean isFilled(){
        return  water != null;
    }

    // --------------------------- работа с  концами ------------------------------------

    public abstract Direction  getSuspendedDirection();


    public boolean hasEnd(Direction direction){
        return ends.contains(direction);
    }

    // ----------------------------- взаимодействие с трубой -----------------------------

    public boolean isCanFilled(PlumbingProduct other){
        return true;
    }

    public boolean isConnected(PlumbingProduct other){
        return true;
    }



}
