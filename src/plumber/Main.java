package plumber;

import plumber.plumber_product.Drain;

public class Main {
    public static void main(String[] args) {

        Drain drain = new Drain(Direction.east(), new Cell(1,1));
        System.out.println("Hello world!");
    }
}