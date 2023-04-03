package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldTest {


    //----------- конструктор тест-----------------


    @Test
    public void constructor_TypeTest() {

        int width = 3;
        int height = 3;

        Field field = new Field(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){

//                System.out.println(i + " " + j);
                Cell cell = field.cell(i, j);
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

        Field field = new Field(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){

//                System.out.println(i + " " + j);
                Cell cell = field.cell(i, j);
                Assertions.assertNotNull(cell);
                Assertions.assertEquals(i,cell.row());
                Assertions.assertEquals(j, cell.col());
            }
        }
    }

    @Test
    public void constructor_InvalidWidth(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Field(1, -1));
    }

    @Test
    public void constructor_InvalidHeight(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Field(-1, 1));
    }


    @Test
    public void constructor_InvalidHeightAndWidth(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Field(-1, -1));
    }
}
