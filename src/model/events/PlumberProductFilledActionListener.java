package model.events;

import java.util.EventListener;

public interface PlumberProductFilledActionListener extends EventListener {

    /** обрабатывает события остановки потока
     *
     * @param  plumberProductFilledActionEvent - эвент
     */
    void plumberProductFilled(PlumberProductFilledActionEvent plumberProductFilledActionEvent);
}
