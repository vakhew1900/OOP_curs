package view;

import model.Game;
import model.Main;
import model.events.GameFinishedActionEvent;
import model.events.GameFinishedActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFinishMessageWidget extends JPanel implements GameFinishedActionListener {



    private final String WIN_MESSAGE = "<html> Вода достигла слива.<br/> Хотите начать новую игру?</html>";
    private final String LOSE_MESSAGE = "<html> Вода не достигла слива. <br/> Хотите начать новую игру? </html>";

    private final String WIN_TITLE = "Вы победили";
    private final String LOSE_TITLE = "Вы проиграли";

    private JLabel title;
    private JLabel message;

    public GameFinishMessageWidget() {
        setVisible(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        title = createTitle();
        message = createMessage();


        add(title);
        add(message);
    }

    private JLabel createTitle(){
        JLabel jLabel = new JLabel();
        jLabel.setFont(new Font("Calibri", Font.BOLD, 28));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }

    private JLabel createMessage(){
        JLabel jLabel = new JLabel();
        jLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }





    public void win(){
        title.setText(WIN_TITLE);
        message.setText(WIN_MESSAGE);
        setVisible(true);
    }

    public void lose(){
        title.setText(LOSE_TITLE);
        message.setText(LOSE_MESSAGE);
        setVisible(true);
    }


    @Override
    public void gameFinished(GameFinishedActionEvent event) {
        Game game = (Game) event.getSource();
        if(game.status() == Game.LOSE){
            lose();
        }
        else{
            win();
        }
    }
}
