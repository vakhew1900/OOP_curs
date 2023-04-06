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
    List<PlumbingProduct> pipeList;

    Plumber(GameField gameField) {
        this.gameField = gameField;
    }


    public void configure() {

        createPipeline();
        source.fill(new Water());
        shufflePipeline();
    }


    void createPipeline() {

        if(gameField.height() == 1 && gameField.width() == 1) {
            throw new IllegalArgumentException("GameField is verysmall");
        }

        Cell startCell = gameField.cell(random(gameField.height()), 0);
        Cell finishCell = gameField.cell(random(gameField.height()), gameField.width() - 1);

        while (startCell == finishCell){
            finishCell = gameField.cell(random(gameField.height()), gameField.width() - 1);
        }


        List<Cell> cellPath = createCellPath(startCell, finishCell);

        List<Direction> directionList = convertCellPathToDirectionPath(cellPath);
        source = new Source(directionList.get(0), cellPath.get(0));
        drain = new Drain(directionList.get(directionList.size() - 1).opposite(),
                cellPath.get(cellPath.size() - 1));

        pipeList = createPipePath(startCell, directionList);

    }


    void shufflePipeline() {

        for (PlumbingProduct plumbingProduct : pipeList) {

            int rotationCnt = random(4);

            for (int i = 0; i < rotationCnt; i++) {
                Pipe pipe = (Pipe) plumbingProduct;
                pipe.rotate();
            }

        }
    }

    private int random(int n) {
        return new Random().nextInt(n);
    }


    private List<Cell> createCellPath(@NotNull Cell startCell, @NotNull Cell finishCell) {


        Map<Cell, Cell> preCell = new HashMap<>();

        dfs(startCell, preCell, startCell);


        if (preCell.get(finishCell) == null) {
            return null;
        }

        List<Cell> cellPath = new ArrayList<>();

        Cell cell = finishCell;


        while (cell != null) {
            cellPath.add(cell);
            cell = preCell.get(cell);
        }

        Collections.reverse(cellPath);

        System.out.println(cellPath.size());

        for (Cell cell1 : cellPath) {
            System.out.println(cell1.row() + " " + cell1.col());
        }

        return cellPath;
    }

    private void dfs(@NotNull Cell currentCell, @NotNull Map<Cell, Cell> preCell, @NotNull Cell startCell) {

        List<Cell> neighborList = new ArrayList<>(currentCell.neighbors().values());
        Collections.shuffle(neighborList);

        for (Cell neighbor : neighborList) {

            boolean isVisited = neighbor.equals(startCell) || preCell.get(neighbor) != null;

            if (isVisited == false) {
                preCell.put(neighbor, currentCell);
                dfs(neighbor, preCell, startCell);
            }
        }
    }

    private List<Direction> convertCellPathToDirectionPath(@NotNull List<Cell> cellPath) {

        List<Direction> directionList = new ArrayList<>();

        for (int i = 1; i < cellPath.size(); i++) {
            Direction direction = cellPath.get(i - 1).neighborDirection(cellPath.get(i));

            if (direction == null) {
                throw new IllegalArgumentException("Illegal createCellPath");
            }

            directionList.add(direction);
        }

        return directionList;
    }

    private List createPipePath(@NotNull Cell cell, @NotNull List<Direction> directionList) {

        List<PlumbingProduct> pipeList = new ArrayList<>();
        cell = cell.neighbor(directionList.get(0));

        for (int i = 1; i < directionList.size(); i++) {
            Set<Direction> directions = new HashSet<>(List.of(new Direction[]{directionList.get(i - 1).opposite(), directionList.get(i)}));
            PlumbingProduct pipe = new Pipe(directions, cell);
            pipeList.add(pipe);
            cell = cell.neighbor(directionList.get(i));
        }

        return pipeList;
    }

}
