package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plumber.*;

public class CellTest {


    @Test
    public void constructorTypeTest(){

        Cell cell = new Cell(4, 7);
        Assertions.assertEquals(7, cell.getCol());
        Assertions.assertEquals(4, cell.getRow());
    }

    @Test
    public void invalidArgumentInConstructorTest(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cell(1, -1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cell(-1, -1));
    }

    @Test
    public void isValidTest(){

        Assertions.assertTrue(Cell.isValid(4, 7));
        Assertions.assertTrue(Cell.isValid(0, 0));
        Assertions.assertFalse(Cell.isValid(-1, 7));
        Assertions.assertFalse(Cell.isValid(1, -1));
        Assertions.assertFalse(Cell.isValid(-100, 1));

    }


    //------------------ тестирование работы с соседями ------------------------

    @Test
    public void neighborTest(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1,2);

        Assertions.assertFalse(cell1.isNeighbor(cell2));

        cell1.setNeighbor(Direction.east(), cell2);
        Assertions.assertTrue(cell1.isNeighbor(cell2));
        Assertions.assertTrue(cell2.isNeighbor(cell1));
    }

    @Test
    public  void cellHasSeveralNeighbors(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1,2);
        Cell cell3 = new Cell(2,1);

        Assertions.assertFalse(cell1.isNeighbor(cell2));
        Assertions.assertFalse(cell1.isNeighbor(cell3));

        cell1.setNeighbor(Direction.east(), cell2);
        cell1.setNeighbor(Direction.north(), cell3);

        Assertions.assertTrue(cell1.isNeighbor(cell2));
        Assertions.assertTrue(cell2.isNeighbor(cell1));

        Assertions.assertTrue(cell1.isNeighbor(cell3));
        Assertions.assertTrue(cell3.isNeighbor(cell1));

        Assertions.assertFalse(cell3.isNeighbor(cell2));
    }

    @Test
    public void setNeighborTwice(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);
        Cell cell3 = new Cell(2, 1);

        cell1.setNeighbor(Direction.east(), cell2);
        cell1.setNeighbor(Direction.east(), cell3);

        Assertions.assertTrue(cell1.isNeighbor(cell2));
        Assertions.assertTrue(cell2.isNeighbor(cell1));

        Assertions.assertFalse(cell1.isNeighbor(cell3));
        Assertions.assertFalse(cell3.isNeighbor(cell1));
    }

    @Test
    public void cellIsOwnNeighbor(){

        Cell cell = new Cell(1, 1);

        cell.setNeighbor(Direction.east(), cell);
        Assertions.assertFalse(cell.isNeighbor(cell));
    }

    @Test
    public void cellHasOneNeighborInAnotherSide(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.south(), cell2);
        cell1.setNeighbor(Direction.east(), cell2);

        Assertions.assertNull(cell1.neighbor(Direction.east()));
        Assertions.assertEquals(cell2, cell1.neighbor(Direction.south()));
    }


}
