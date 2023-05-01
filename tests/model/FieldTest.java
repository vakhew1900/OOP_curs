package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldTest {


    //----------- конструктор тест-----------------


    @Test
    public void constructor_TypeTest() {

        int width = 3;
        int height = 3;

        GameField gameField = new GameField(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){

//                System.out.println(i + " " + j);
                Cell cell = gameField.cell(i, j);
                Assertions.assertNotNull(cell);
                Assertions.assertEquals(i,cell.row());
                Assertions.assertEquals(j, cell.col());
            }
        }
    }

    @Test
    public void constructor_TypeTest2() {

        int width = 4;
        int height = 7;

        GameField gameField = new GameField(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){

//                System.out.println(i + " " + j);
                Cell cell = gameField.cell(i, j);
                Assertions.assertNotNull(cell);
                Assertions.assertEquals(i,cell.row());
                Assertions.assertEquals(j, cell.col());
            }
        }
    }


    @Test
    public void constructor_cellIsField() {

        int width = 1;
        int height = 1;

        GameField gameField = new GameField(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){

//                System.out.println(i + " " + j);
                Cell cell = gameField.cell(i, j);
                Assertions.assertNotNull(cell);
                Assertions.assertEquals(i,cell.row());
                Assertions.assertEquals(j, cell.col());
            }
        }
    }

    @Test
    public void constructor_InvalidWidth(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameField(1, -1));
    }

    @Test
    public void constructor_InvalidHeight(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameField(-1, 1));
    }


    @Test
    public void constructor_InvalidHeightAndWidth(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameField(-1, -1));
    }


    //------------------ cell ----------------------------------

    @Test
    public void cell_TypeTest(){

        GameField gameField = new GameField(3, 3);

        Cell cell = gameField.cell(2, 1);

        Assertions.assertEquals(2,cell.row());
        Assertions.assertEquals(1, cell.col());

        cell = gameField.cell(0, 0);

        Assertions.assertEquals(0,cell.row());
        Assertions.assertEquals(0, cell.col());

        cell = gameField.cell(2, 2);

        Assertions.assertEquals(2,cell.row());
        Assertions.assertEquals(2, cell.col());

    }

    @Test
    public void cell_illegalArgumentTest(){

        GameField gameField = new GameField(3, 3);

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameField.cell(-1, -1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameField.cell(1, -1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameField.cell(-1, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameField.cell(3, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameField.cell(1, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameField.cell(3, 1));
    }

}
