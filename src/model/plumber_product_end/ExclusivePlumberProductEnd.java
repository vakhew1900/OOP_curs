package model.plumber_product_end;

import model.Direction;
import model.Plumber;
import model.material.Materiable;
import model.material.Material;

public class ExclusivePlumberProductEnd extends PlumberProductEnd implements Materiable {
    /**
     * Конструктор
     *
     * @param direction - направление трубы
     */

    private Material exclusiveMaterial;



    private ExclusivePlumberProductEnd(Direction direction, int diameter, Material material) {
        super(direction, diameter, material);
    }

    public   ExclusivePlumberProductEnd(Direction direction, int diameter, Material material, Material  exclusiveMaterial) {
        super(direction, diameter, material);

        if(exclusiveMaterial.equals(material)){
            throw new IllegalArgumentException("illegal exclusiveMaterial");
        }

       this.exclusiveMaterial = exclusiveMaterial;
    }


    @Override
    public boolean equals(Object other) {

        if (other instanceof ExclusivePlumberProductEnd){

            ExclusivePlumberProductEnd exclusivePlumberProductEnd = (ExclusivePlumberProductEnd) other;
            return super.equals(exclusivePlumberProductEnd) && this.exclusiveMaterial.equals(((ExclusivePlumberProductEnd) other).exclusiveMaterial);
        }

        return false;
    }

    @Override
    public boolean isCanConnected(AbstractPlumberProductEnd abstractPlumberProductEnd) {

        if (abstractPlumberProductEnd instanceof PlumberProductEnd){
            PlumberProductEnd end = (PlumberProductEnd) abstractPlumberProductEnd;
            Material otherMaterial = ((PlumberProductEnd) abstractPlumberProductEnd).material();
            boolean res = this.diameter() == end.diameter() && end.direction().opposite().equals(this.direction());
            if (exclusiveMaterial.getClass().isAssignableFrom(otherMaterial.getClass())){
                res = false;
            }

            return res;
        }

        return true;
    }

    public Material exclusiveMaterial(){
        return exclusiveMaterial;
    }


    @Override
    public int hashCode() {
        return material().hashCode() * 100*1000 + diameter() * 1000 + direction().hashCode();
    }


}
