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


    public void configure(){
        createPipeline();
        disassemblePipeline();
    }


    private void createPipeline(){
        try {

            Cell startCell = gameField.cell(random(gameField.height()), 0);
            Cell finishCell = gameField.cell(random(gameField.height()), gameField.width() - 1);

            List<Cell> cellPath = new ArrayList<>();
            List<Cell> requiredCells = createRequiredCells(3, startCell, finishCell);

            for (int i = 0; i < requiredCells.size() - 1; i++) {

                List<Cell> tmpList = createCellPath(requiredCells.get(i), requiredCells.get(i + 1), cellPath, requiredCells);
                cellPath.addAll(tmpList);

                cellPath.remove(requiredCells.get(i + 1));
            }

            cellPath.add(finishCell);

            List<Direction> directionList = convertCellPathToDirectionPath(cellPath);
            source = new Source(directionList.get(0), cellPath.get(0));
            drain = new Drain(directionList.get(directionList.size() - 1).opposite(),
                    cellPath.get(cellPath.size() - 1));

            pipeList = createPipePath(startCell, directionList);

            source.fill(new Water());
        }
        catch (NullPointerException e){
            createPipeline();
        }
    }


    private void disassemblePipeline(){

        for(PlumbingProduct plumbingProduct : pipeList){

            int rotationCnt = random(4);

            for(int i = 0; i < rotationCnt; i++){
                Pipe pipe = (Pipe) plumbingProduct;
                pipe.rotate();
            }

        }
    }

    private int random(int n) {
        return new Random().nextInt(n);
    }



    private List<Cell> createCellPath(@NotNull Cell startCell, @NotNull Cell finishCell, List<Cell> visitedCells, List<Cell> requiredCells) {

        Queue<Cell> queue = new LinkedList<>();
        queue.add(startCell);

        Map<Cell, Cell> preCell = new HashMap<>();


        while (!queue.isEmpty()) {

            Cell currentCell = queue.remove();
            List<Cell>  neighborList = new ArrayList<>(currentCell.neighbors().values());
            Collections.shuffle(neighborList);
            for (Cell neighbor : neighborList) {
                boolean isVisitedCell =  neighbor.equals(startCell) == true || (visitedCells != null && visitedCells.contains(neighbor));
                boolean isUnexpectedRequiredCell = neighbor.equals(finishCell) == false && requiredCells.contains(neighbor);

                if (preCell.get(neighbor) == null && isVisitedCell == false && isUnexpectedRequiredCell == false) {
                    preCell.put(neighbor, currentCell);
                    queue.add(neighbor);
                }

            }
        }

        System.out.println("-----------------------");

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
