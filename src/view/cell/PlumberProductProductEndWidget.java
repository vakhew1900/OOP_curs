package view.cell;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;

import java.awt.*;

public class PlumberProductProductEndWidget extends AbstractPlumberProductEndWidget {

    protected Color waterColor = new Color(5, 225, 225);

    public PlumberProductProductEndWidget(AbstractPlumberProductEnd plumberProductEnd) {
        super(plumberProductEnd);
    }

    @Override
    protected String getFileName() {

        String res = "big_end.png";

        if(plumberProductEnd() instanceof PlumberProductEnd){
            if(((PlumberProductEnd)plumberProductEnd()).diameter() == PlumberProductEnd.SMALL_DIAMETER){
                res = "small_end.png";
            }
        }

        return res;
    }
}
