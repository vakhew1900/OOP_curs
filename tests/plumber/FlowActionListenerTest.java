package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.events.FlowActionEvent;
import plumber.events.FlowActionListener;
import plumber.plumber_product.Drain;

public class FlowActionListenerTest  {

    private class FlowObserver implements FlowActionListener {

        private int eventCnt =0;

        @Override
        public void flowStopped(FlowActionEvent event) {
            incrementEventCnt();
        }

        private void  incrementEventCnt(){
            eventCnt++;
        }

        public int eventCnt(){
            return eventCnt;
        }
    }

    private FlowObserver flowObserver;
    private Water water;
    private Drain drain;

    @BeforeEach
    private void init(){
        flowObserver = new FlowObserver();
        water = new Water();
        drain = new Drain(Direction.west(), new Cell(1, 1));
    }


    @Test
    public void flowObserverNotListenTest(){

        water.fireFlowAction();
        Assertions.assertEquals(0,flowObserver.eventCnt());
    }

    @Test
    public void flowObserverListenOneObject(){
        water.addFlowActionListener(flowObserver);

        water.fireFlowAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());
        Assertions.assertEquals(1,flowObserver.eventCnt());

        water.fireFlowAction();
        Assertions.assertEquals(2,flowObserver.eventCnt());
    }

    @Test
    public void flowObserverListenOneObjectTwice(){
        water.addFlowActionListener(flowObserver);
        water.addFlowActionListener(flowObserver);

        water.fireFlowAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());
    }


    @Test
    public void flowObserverListenTwoObject(){


        water.addFlowActionListener(flowObserver);
        drain.addFlowActionListener(flowObserver);

        water.fireFlowAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());

        drain.fill(water);
        Assertions.assertEquals(2, flowObserver.eventCnt);
    }

    @Test
    public void flowObserverStopListen(){

        water.addFlowActionListener(flowObserver);
        water.fireFlowAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());

        water.removeFlowActionListener(flowObserver);
        water.fireFlowAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());
    }



}
