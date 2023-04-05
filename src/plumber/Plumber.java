package plumber;

import org.jetbrains.annotations.NotNull;
import plumber.plumber_product.Drain;
import plumber.plumber_product.Pipe;
import plumber.plumber_product.PlumbingProduct;
import plumber.plumber_product.Source;

import java.util.*;

public class Plumber {

    private GameField gameField;
    PlumbingProduct source = null, drain = null;
    List<PlumbingProduct> pipes;

    Plumber(GameField gameField) {
        this.gameField = gameField;
    }


    public void configure(){


        Cell startCell = gameField.cell(random(gameField.height()), 0);
        Cell finishCell = gameField.cell(random(gameField.height()), gameField.width() - 1);

        List<Cell> cellPath = createCellPath(startCell, finishCell);
        List<Direction> directionList = convertCellPathToDirectionPath(cellPath);

        PlumbingProduct source = new Source(directionList.get(0), cellPath.get(0));
        PlumbingProduct drain = new Drain(directionList.get(directionList.size() - 1).opposite(),
                                            cellPath.get(cellPath.size() -1));

        List<Pipe> pipeList = createPipePath(startCell, directionList);

        source.fill(new Water());

    }



    private int random(int n) {
        return new Random().nextInt(n);
    }



    private List<Cell> createCellPath(@NotNull Cell startCell, @NotNull Cell finishCell) {

        Queue<Cell> queue = new LinkedList<>();
        queue.add(startCell);

        Map<Cell, Cell> preCell = new HashMap<>();


       // System.out.println("ffff");
        while (!queue.isEmpty()) {

            Cell currentCell = queue.remove();
            List<Cell>  neighborList = new ArrayList<>(currentCell.neighbors().values());
            Collections.shuffle(neighborList);
            for (Cell neighbor : neighborList) {
                if (preCell.get(neighbor) == null && neighbor.equals(startCell) == false) {
                    preCell.put(neighbor, currentCell);
                    queue.add(neighbor);
                }

            }
        }

        //System.out.println("-----------------------");

        if (preCell.get(finishCell) == null) {
            return null;
        }

        List<Cell> cellPath = new ArrayList<>();

        Cell cell = finishCell;

        for(Map.Entry<Cell, Cell>  entry: preCell.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


        while (cell != null) {
            cellPath.add(cell);
            cell = preCell.get(cell);
        }

        Collections.reverse(cellPath);

        System.out.println(cellPath.size());

        for(Cell cell1 : cellPath){
            System.out.println(cell1.row() + " " + cell1.col());
        }

        return cellPath;
    }

    private List<Direction> convertCellPathToDirectionPath(@NotNull List<Cell> cellPath){

        List<Direction> directionList = new ArrayList<>();

        for(int i = 1; i < cellPath.size(); i++){
            Direction direction = cellPath.get(i - 1).neighborDirection(cellPath.get(i));

            if (direction == null){
                throw  new IllegalArgumentException("Illegal createCellPath");
            }

            directionList.add(direction);
        }

        return directionList;
    }

    private List createPipePath(@NotNull Cell cell,@NotNull List<Direction> directionList){

        List<PlumbingProduct> pipeList = new ArrayList<>();
        cell = cell.neighbor(directionList.get(0));

        for(int i = 1; i < directionList.size(); i++){
            Set<Direction> directions = new HashSet<>(List.of(new Direction[]{directionList.get(i - 1).opposite(), directionList.get(i)}));
            PlumbingProduct pipe = new Pipe(directions, cell);
            pipeList.add(pipe);
        }

        return pipeList;
    }


    private List<Cell> createRequiredCells(int size, @NotNull Cell startCell, @NotNull Cell finishCell){

        if(size < 2){
            throw new IllegalArgumentException("Illegal size");
        }

        List<Cell> requiredCells = new ArrayList<>();
        requiredCells.add(startCell);

        while (requiredCells.size() + 1 != size){

            int randomRow = random(gameField.height());
            int randomCol = random(gameField.width());

            Cell cell = gameField.cell(randomRow, randomCol);

            if(requiredCells.contains(cell) == false && cell.equals(finishCell) == false){
                requiredCells.add(cell);
            }
        }

        requiredCells.add(finishCell);

        return requiredCells;
    }



}
