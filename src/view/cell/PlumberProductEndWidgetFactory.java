package view.cell;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.ExclusivePlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;

public class PlumberProductEndWidgetFactory {



    public AbstractPlumberProductEndWidget create(AbstractPlumberProductEnd abstractPlumberProductEnd){


        if (abstractPlumberProductEnd instanceof ExclusivePlumberProductEnd){
            return new ExclusivePlumberProductEndWidget((ExclusivePlumberProductEnd) abstractPlumberProductEnd);
        }

        return  new PlumberProductProductEndWidget(abstractPlumberProductEnd);
    }



}
