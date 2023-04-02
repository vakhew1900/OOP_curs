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
        cell.fill(this);
    }


    // --------------------- взаимодействие с водой ----------------------------------

    private Water water = null;
    public void  fill(Water water){

        if(isFilled() == false) {
            this.water = water;
            if (water.getLastFillingPlumbingProduct() == null || water.getLastFillingPlumbingProduct().equals(this) == false) {
                water.nextPlumbingProduct(this);
            }
        }
    }

    public  boolean isFilled(){
        return  water != null;
    }

    // --------------------------- работа с  концами ------------------------------------

    public abstract Direction  getSuspendedDirection();


    public boolean hasEnd(Direction direction){
        return ends.contains(direction);
    }

    private PlumbingProduct neighbor(Direction direction){

        Cell neighborCell = cell.neighbor(direction);

        if (neighborCell != null){
            return neighborCell.getContent();
        }
        return  null;
    }

    // ----------------------------- взаимодействие с трубой -----------------------------

    public boolean isCanFilled(PlumbingProduct other){
        return other != null && other.isFilled() == false && isConnected(other);
    }

    public boolean isConnected(PlumbingProduct other){

        if(other == null)
            return false;

        Direction direction = null;

        for(Direction elem : ends){

            if(neighbor(elem) != null && neighbor(elem).equals(other) ){
                direction = elem;
            }
        }

        return direction != null && other.hasEnd(direction.opposite());
    }



    //------------------------ Системные ----------------------------

}
