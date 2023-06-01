package view;

import model.Cell;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.cell.CellWidget;

import javax.swing.*;
import java.awt.*;

public class GameFieldWidget extends JPanel {

    /**
     *  игровое поле
     */
    GameField gameField;

    /**
     * Конструктор
     * @param gameField - игровое поле
     */
    public GameFieldWidget(@NotNull GameField gameField){
        this.gameField = gameField;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillField();

    }


    /**
     * Заполнить поле клетками и игровыми объектами
     */
    private void fillField(){

        for(int i = 0; i < gameField.width(); i++){
            JPanel row = createRow(i);
            add(row);
            System.out.println(i);
        }
    }

    /** Создать одну строку игрового поля
     *
     * @param rowIndex - индекс строки
     * @return - виджет одной строки игрового поля
     */
    private JPanel createRow(int rowIndex){
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for(int i = 0; i < gameField.width(); i++){
            Cell cell = gameField.cell(rowIndex, i);

            CellWidget cellWidget = new CellWidget(cell);
            row.add(cellWidget);
        }

        return row;
    }
}
