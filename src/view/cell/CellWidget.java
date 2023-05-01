package view.cell;

import model.Cell;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CellWidget extends JPanel {

    private Cell cell;
    private static final int CELL_SIZE = 80;
    private Color CELL_COLOR = Color.decode("#70FE19");
    public CellWidget(@NotNull Cell cell) {
        this.cell = cell;
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(CELL_COLOR);

        //границы
        setBorder(BorderFactory.createLineBorder(Color.black));
    }


}
