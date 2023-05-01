package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.events.WaterStoppedActionEvent;
import model.events.WaterStoppedActionListener;
import model.plumber_product.Drain;

public class WaterStoppedActionListenerTest {

    private class FlowObserver implements WaterStoppedActionListener {

        private int eventCnt =0;

        @Override
        public void waterStopped(WaterStoppedActionEvent event) {
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

        water.fireWaterAction();
        Assertions.assertEquals(0,flowObserver.eventCnt());
    }

    @Test
    public void flowObserverListenOneObject(){
        water.addWaterStoppedActionListener(flowObserver);

        water.fireWaterAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());
        Assertions.assertEquals(1,flowObserver.eventCnt());

        water.fireWaterAction();
        Assertions.assertEquals(2,flowObserver.eventCnt());
    }

    @Test
    public void flowObserverListenOneObjectTwice(){
        water.addWaterStoppedActionListener(flowObserver);
        water.addWaterStoppedActionListener(flowObserver);

        water.fireWaterAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());
    }


    @Test
    public void flowObserverListenTwoObject(){


        water.addWaterStoppedActionListener(flowObserver);
        Water water2 = new Water();
        water2.addWaterStoppedActionListener(flowObserver);

        water.fireWaterAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());

        water2.fireWaterAction();
        Assertions.assertEquals(2, flowObserver.eventCnt);
    }

    @Test
    public void flowObserverStopListen(){

        water.addWaterStoppedActionListener(flowObserver);
        water.fireWaterAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());

        water.removeFlowActionListener(flowObserver);
        water.fireWaterAction();
        Assertions.assertEquals(1,flowObserver.eventCnt());
    }



}
