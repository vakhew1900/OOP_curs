package plumber.events;

import java.util.EventListener;

public interface FlowActionListener extends EventListener {

    void flowStopped(FlowActionEvent event);
}
