package view;

import model.Cell;
import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class GameFieldWidget extends JPanel {

    GameField gameField;


    public GameFieldWidget(@NotNull GameField gameField){
        this.gameField = gameField;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillField();

    }

    private void fillField(){

        for(int i = 0; i < gameField.width(); i++){
            JPanel row = createRow(i);
            add(row);
            System.out.println(i);
        }
    }

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
