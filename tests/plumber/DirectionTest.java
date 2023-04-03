package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void equalsTest(){

        //true
        Assertions.assertEquals(Direction.north(), Direction.north());
        Assertions.assertEquals(Direction.west(), Direction.west());
        Assertions.assertEquals(Direction.east(), Direction.east());
        Assertions.assertEquals(Direction.south(), Direction.south());

        //false
        Assertions.assertNotEquals(Direction.north(), Direction.south());
        Assertions.assertNotEquals(Direction.west(), Direction.south());
        Assertions.assertNotEquals(Direction.east(), Direction.south());

        Assertions.assertNotEquals(Direction.south(), Direction.north());
        Assertions.assertNotEquals(Direction.west(), Direction.north());
        Assertions.assertNotEquals(Direction.east(), Direction.north());

        Assertions.assertNotEquals(Direction.south(), Direction.west());
        Assertions.assertNotEquals(Direction.east(), Direction.west());
        Assertions.assertNotEquals(Direction.north(), Direction.west());

        Assertions.assertNotEquals(Direction.south(), Direction.east());
        Assertions.assertNotEquals(Direction.west(), Direction.east());
        Assertions.assertNotEquals(Direction.north(), Direction.east());


    }

    @Test
    public void opositeTest(){

        //east
        Direction expected = Direction.west();
        Direction result = Direction.east().opposite();
        Assertions.assertEquals(expected, result);

        //west
        expected = Direction.east();
        result = Direction.west().opposite();
        Assertions.assertEquals(expected, result);

        //north
        expected = Direction.south();
        result = Direction.north().opposite();
        Assertions.assertEquals(expected, result);

        //south
        expected = Direction.north();
        result = Direction.south().opposite();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void isOppositeTestWithResultTrue(){

        //east
        Assertions.assertTrue(Direction.east().isOpposite(Direction.west()));

        //west
        Assertions.assertTrue(Direction.west().isOpposite(Direction.east()));

        //north
        Assertions.assertTrue(Direction.north().isOpposite(Direction.south()));

        //south
        Assertions.assertTrue(Direction.south().isOpposite(Direction.north()));
    }


    @Test
    public void clockwiseTest(){

        Assertions.assertEquals(Direction.east(), Direction.north().clockwise());
        Assertions.assertEquals(Direction.north(), Direction.west().clockwise());
        Assertions.assertEquals(Direction.west(), Direction.south().clockwise());
        Assertions.assertEquals(Direction.south(), Direction.east().clockwise());
    }

    @Test
    public void anticloweTest(){
        Assertions.assertEquals(Direction.west(), Direction.north().anticlockwise());
        Assertions.assertEquals(Direction.north(), Direction.east().anticlockwise());
        Assertions.assertEquals(Direction.east(), Direction.south().anticlockwise());
        Assertions.assertEquals(Direction.south(), Direction.west().anticlockwise());
    }

    @Test
    public void isOppositeTestWithResultFalse(){

        //east
        Assertions.assertFalse(Direction.east().isOpposite(Direction.east()));
        Assertions.assertFalse(Direction.east().isOpposite(Direction.north()));
        Assertions.assertFalse(Direction.east().isOpposite(Direction.south()));

        //west
        Assertions.assertFalse(Direction.west().isOpposite(Direction.west()));
        Assertions.assertFalse(Direction.west().isOpposite(Direction.north()));
        Assertions.assertFalse(Direction.west().isOpposite(Direction.south()));

        //south
        Assertions.assertFalse(Direction.south().isOpposite(Direction.west()));
        Assertions.assertFalse(Direction.south().isOpposite(Direction.south()));
        Assertions.assertFalse(Direction.south().isOpposite(Direction.east()));

        //north
        Assertions.assertFalse(Direction.north().isOpposite(Direction.west()));
        Assertions.assertFalse(Direction.north().isOpposite(Direction.east()));
        Assertions.assertFalse(Direction.north().isOpposite(Direction.north()));
    }


}
