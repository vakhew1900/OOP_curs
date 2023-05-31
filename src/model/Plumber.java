package model;

import model.material.Material;
import model.material.Metal;
import model.material.Plastic;
import model.material.Steel;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.jetbrains.annotations.NotNull;
import model.plumber_product.Drain;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;

import java.util.*;

public class Plumber extends AbstractPlumber {



    private List<Material> materials = new ArrayList<>(Arrays.<Material>asList(new Metal(), new Plastic(), new Steel()));
    private List<Integer> diameters = new ArrayList<>(Arrays.asList(Integer.valueOf(PlumberProductEnd.BIG_DIAMETER), Integer.valueOf(PlumberProductEnd.SMALL_DIAMETER)));

    /**
     * Конструктор
     *
     * @param gameField - игровое поле
     */
    Plumber(GameField gameField) {
        super(gameField);
    }


    /**
     * Создать собранный водопровод. По данному водопровооду вода может дойти от источника до слива
     */
    @Override
    protected Source createSource(Direction direction, Cell cell) {
        AbstractPlumberProductEnd sourcePlumberProductEnd = new PlumberProductEnd(direction, diameters.get(random(diameters.size())), materials.get(random(materials.size())));

        return new Source(sourcePlumberProductEnd, cell);
    }

    @Override
    protected Drain createDrain(Direction direction, Cell cell) {
        PlumbingProduct lastPlumberProduct = (pipeList().size() > 0)? pipeList().get(pipeList().size() - 1) : source();

        AbstractPlumberProductEnd drainPlumberProductEnd = new PlumberProductEnd((PlumberProductEnd) lastPlumberProduct.getEnd(direction)).opposite();
        return new Drain(drainPlumberProductEnd, cell);
    }



    @Override
    protected Pipe createPipe(List<Direction> directions, Cell cell, List<Pipe> pipeList) {

        PlumberProductEnd predPlumberProductEnd;
        Direction predDirection = directions.get(0);
        if (pipeList.size() == 0) {
            predPlumberProductEnd = (PlumberProductEnd) source().getEnd(predDirection);
        } else {
            predPlumberProductEnd = (PlumberProductEnd) pipeList.get(pipeList.size() - 1).getEnd(predDirection);
        }


        AbstractPlumberProductEnd leftPlumberProductEnd = new PlumberProductEnd((PlumberProductEnd) predPlumberProductEnd.opposite());
        AbstractPlumberProductEnd rightPlumberProductEnd;


        Direction direction = directions.get(1);
        Material material = predPlumberProductEnd.material();
        int diameter = predPlumberProductEnd.diameter();
        int tmp = 3;
        if (random(tmp) == 1) {
            material = materials.get(random(materials.size()));
            diameter = diameters.get(random(diameters.size()));

        }

        rightPlumberProductEnd = new PlumberProductEnd(direction, diameter, material);


        Set<AbstractPlumberProductEnd> directionSet = new HashSet<>(List.of(new AbstractPlumberProductEnd[]{leftPlumberProductEnd, rightPlumberProductEnd}));

        return  new Pipe(directionSet, cell);
    }

}
