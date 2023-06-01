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


    /**
     * Сообщение о победе пользователя
     */
    private final String WIN_MESSAGE = "<html> Вода достигла слива.<br/> Хотите начать новую игру?</html>";


    /**
     * Сообщение о проигрыше пользователя
     */
    private final String LOSE_MESSAGE = "<html> Вода не достигла слива. <br/> Хотите начать новую игру? </html>";

    /**
     *  Надпись Заголовка победы пользователя
     */
    private final String WIN_TITLE = "Вы победили";

    /**
     * Надпись Заголовка проигрыша пользователя
     */
    private final String LOSE_TITLE = "Вы проиграли";

    /**
     *  Виджет заголовка пользователя
     */
    private JLabel title;


    /**
     * Виджет сообщения пользователя
     */
    private JLabel message;

    /**
     * Конструктор
     */
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

    /** Создать сообщение о победе/проигрыше игрока
     *
     * @return
     */
    private JLabel createMessage(){
        JLabel jLabel = new JLabel();
        jLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }


    /**
     *  Вывести информацию о победе пользователя
     */
    public void win(){
        title.setText(WIN_TITLE);
        message.setText(WIN_MESSAGE);
        setVisible(true);
    }

    /**
     * Вывести информацию о проигрыше игрока
     */
    public void lose(){
        title.setText(LOSE_TITLE);
        message.setText(LOSE_MESSAGE);
        setVisible(true);
    }


    /**
     * Обработка события завершения игры
     * @param event - эвент завершения игры
     */
    @Override
    public void gameFinished(GameFinishedActionEvent event) {
        Game game = (Game) event.getSource();

        System.out.println(game.status());
        if(game.status() == Game.LOSE){
            lose();
        }
        else{
            win();
        }
    }
}
