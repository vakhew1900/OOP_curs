package plumber.events;

import java.util.EventObject;

public class FlowActionEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FlowActionEvent(Object source) {
        super(source);
    }
}
