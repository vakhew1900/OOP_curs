package plumber;

import org.jetbrains.annotations.NotNull;

import java.util.*;

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

        buildField();
    }


//------------------------------Геттеры --------------------------------------------

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }

    private void buildField() {

        // Создаем ячейки
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                Cell cell = new Cell(row, col);
                cellList.add(cell);
            }
        }

        // Связываем ячейки
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {

                Cell cell = cell(row, col);

                if (height() > 1 && row < height() - 1) {
                   cell.setNeighbor(Direction.south(), cell(row + 1, col));
                }
                if (row > 0) {
                    cell.setNeighbor(Direction.north(), cell(row - 1, col));
                }
                if (width() > 1 && col < width() - 1) {
                    cell.setNeighbor(Direction.east(), cell(row, col + 1));
                }
                if (col > 0) {
                    cell.setNeighbor(Direction.west(), cell(row, col - 1));
                }
            }
        }
    }


    public Cell cell(int row, int col){

        int index = row * width() + col;
        System.out.println("row :"+ row + " col:" + col + "index:" + index);
        return  cellList.get(row * width() + col);
    }






}
