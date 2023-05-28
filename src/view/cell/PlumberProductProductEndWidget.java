package view.cell;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;

public class PlumberProductProductEndWidget extends AbstractPlumberProductEndWidget {


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
