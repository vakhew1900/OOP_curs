package plumber.events;

import java.util.EventListener;

public interface FlowActionListener extends EventListener {

    /** обрабатывает события остановки потока
     *
     * @param event - евент
     */
    void flowStopped(FlowActionEvent event);
}
