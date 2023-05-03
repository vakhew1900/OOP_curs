package model;

import view.GameFieldWidget;
import view.GameFinishMessageWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(GamePanel::new);

    }

    static class GamePanel extends JFrame {

        Game game;

        public GamePanel() throws HeadlessException {

            game = new Game();
            JPanel mainPanel = new JPanel();
            GameFieldWidget gameFieldWidget = new GameFieldWidget(game.gamefield());
            mainPanel.add("CENTER", gameFieldWidget);
            JPanel managePanel = createManagePanel();
            mainPanel.add("EAST", managePanel);
            add(mainPanel);

            defaultSetting();


        }

        public void restart(){

        }

        private JPanel createManagePanel() {

            int WIDTH = 240;
            int HEIGHT = 160;

            JPanel managePanel = new JPanel();
            managePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            GameFinishMessageWidget gameFinishMessageWidget = new GameFinishMessageWidget();
            game.addGameFinishedActionListener(gameFinishMessageWidget);
            managePanel.add(gameFinishMessageWidget);

            JButton flowWatterButton = new JButton("Открыть кран");
            flowWatterButton.addActionListener(new WaterStartAction());
            flowWatterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            ;
            managePanel.add("SOUTH", flowWatterButton);
            managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));
            return managePanel;
        }

        private void defaultSetting() {
            setVisible(true);
            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

        }

       private class WaterStartAction implements ActionListener{

           @Override
           public void actionPerformed(ActionEvent e) {
               if(game.status() == game.RUNNING) {
                   game.flowWater();
                   ((JButton)e.getSource()).setEnabled(false);
               }
           }
       }
    }

}