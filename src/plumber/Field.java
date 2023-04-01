package plumber;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private int height = 10;
    private int width = 10;
    private List<Cell> cellList = new ArrayList<>();

    private Plumber plumber;


//   ------------------------------ Конструктор ---------------------------------------
    public Field(){
        new Field(10, 10);
    }
    public Field(int height, int width){

        if(height <= 0 || width <= 0){
            throw  new IllegalArgumentException("illegal height or illegal width");
        }

        this.height = height;
        this.width = width;
    }





}
