package model.events;

import java.util.EventListener;

public interface GameFinishedActionListener extends EventListener {

    /**  Сообщение о завершении игры
     *
     * @param event - эвент завершения игры
     */
    void gameFinished(GameFinishedActionEvent event);
}
