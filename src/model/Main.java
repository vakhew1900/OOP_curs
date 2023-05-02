package model;

import view.GameFieldWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
            addKeyListener(new MyKeyListener());
        }

        private void defaultSetting(){
            setVisible(true);
            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        private class MyKeyListener extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {

                System.out.println(e.getKeyCode());
                if(e.getKeyCode() == KeyEvent.VK_C){
                    game.flowWater();
                }
            }


        }
    }

}