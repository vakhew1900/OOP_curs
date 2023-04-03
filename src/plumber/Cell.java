package plumber;

import org.jetbrains.annotations.NotNull;
import plumber.plumber_product.PlumbingProduct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cell {

    private int row;
    private int col;

    private boolean isFilled = false;

    //------------------------------- Конструктор ---------------------------------------

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public Cell(int row, int col) {

        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("illegal x or y");
        }

        this.row = row;
        this.col = col;
    }


    public static boolean isValid(int col, int row) {
        return col >= 0 && row >= 0;
    }

    //------------------------ работа с содержимым -------------------------------------------------

    private PlumbingProduct plumbingProduct;

    public void fill(PlumbingProduct plumbingProduct) {

        this.plumbingProduct = plumbingProduct;
    }

    public  void clear() {
        this.plumbingProduct = null;
    }

    public boolean isFilled() {
        return plumbingProduct != null;
    }

    public PlumbingProduct getContent() {
        return plumbingProduct;
    }


    // ---------------------- Соседи -----------------------
    private final Map<Direction, Cell> _neighbors = new HashMap<>();

    public Cell neighbor(Direction direct) {

        if (_neighbors.containsKey(direct)) {
            return _neighbors.get(direct);
        }

        return null;
    }

    public Map<Direction, Cell> neighbors() {
        return Collections.unmodifiableMap(_neighbors);
    }

    public void setNeighbor(@NotNull Direction direct, @NotNull  Cell neighbor) {
        if (neighbor != this && !isNeighbor(neighbor) && this.neighbor(direct) == null) {
            _neighbors.put(direct, neighbor);
            neighbor.setNeighbor(direct.opposite(), this);
        }
    }

    public boolean isNeighbor(Cell other) {
        return _neighbors.containsValue(other);
    }


}
