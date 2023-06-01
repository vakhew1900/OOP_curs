package view.cell;

import model.Cell;
import model.Plumber;
import model.plumber_product.Drain;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CellWidget extends JPanel {

    /**
     * Клетка игрового поля
     */
    private Cell cell;

    /**
     * Размер виджета клетки
     */
    public static final int CELL_SIZE = 80;

    /**
     *  Цвет клетки
     */
    private Color CELL_COLOR = Color.decode("#70FE19");

    /**
     * Конструктор
     * @param cell - клетка игрового поля
     */
    public CellWidget(@NotNull Cell cell) {
        this.cell = cell;
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(CELL_COLOR);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        PlumbingProduct plumbingProduct = cell.getPlumbingProduct();

        if (plumbingProduct != null) {
            PlumberProductWidget plumberProductWidget = null;

            if (plumbingProduct instanceof Pipe) {
                plumberProductWidget = new PipeWidget((Pipe) plumbingProduct);
            } else if (plumbingProduct instanceof Source) {
                plumberProductWidget = new SourceWidget((Source) plumbingProduct);
            } else if (plumbingProduct instanceof Drain) {
                plumberProductWidget = new DrainWidget((Drain) plumbingProduct);
            }
            add(plumberProductWidget);

        }
    }


}
