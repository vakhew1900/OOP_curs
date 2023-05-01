package model;

import view.GameFieldWidget;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(GamePanel::new);

    }

    static class GamePanel extends JFrame {

        Game game;
        public GamePanel() throws HeadlessException {

            game = new Game();
            GameFieldWidget gameFieldWidget = new GameFieldWidget(game.gamefield());
            add(gameFieldWidget);
            defaultSetting();
        }

        private void defaultSetting(){
            setVisible(true);
            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

}