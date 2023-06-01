package view.cell;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;

public class MiniPlumberProductEndWidget extends AbstractPlumberProductEndWidget {

    /**
     * Конструктор
     * @param plumberProductEnd - конец трубы
     */
    public MiniPlumberProductEndWidget(AbstractPlumberProductEnd plumberProductEnd) {
        super(plumberProductEnd);
    }

    @Override
    protected String getFileName() {
        String res = "mini_end.png";

        if (plumberProductEnd() instanceof PlumberProductEnd && ((PlumberProductEnd) plumberProductEnd()).diameter() == PlumberProductEnd.SMALL_DIAMETER){
            res = "mini_small_end.png";
        }

        return res;
    }
}
