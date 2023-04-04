package plumber;

import org.jetbrains.annotations.NotNull;
import plumber.events.FlowActionEvent;
import plumber.events.FlowActionListener;
import plumber.plumber_product.PlumbingProduct;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Water  implements ActionListener {


    private PlumbingProduct lastFillingPlumbingProduct;
    private Direction waterDirection;
    private Timer timer;
    private int timeout = 2000;
    private boolean isStopped = false;

    public Water(){
        this(2000);
    }


    public Water(int timeout){
        if (timeout < 1)
            throw  new IllegalArgumentException("Illegal timeoutargument");

        this.timeout = timeout;
        timer = new Timer(timeout, this);
    }

    public PlumbingProduct getLastFillingPlumbingProduct(){
        return lastFillingPlumbingProduct;
    }

    public void flow(){

        if(timer.isRunning() == false && isStopped == false){
            timer.start();
        }

        boolean result = false;
        for(Direction direction :  getLastFillingPlumbingProduct().getEnds()){
            result = nextConnection(direction);

            if(result == true){
                break;
            }
        }

        isStopped = !result || isStopped;
    }

     public void nextPlumbingProduct(@NotNull PlumbingProduct plumbingProduct){


        if(plumbingProduct.isFilled() == false) {
            plumbingProduct.fill(this);
        }

        if (this.equals(plumbingProduct.water()))
            this.lastFillingPlumbingProduct = plumbingProduct;

    }

    private boolean nextConnection(@NotNull Direction direction){

        if(getLastFillingPlumbingProduct() == null)
            return false;

        boolean result = true;
        PlumbingProduct neighbor =  getLastFillingPlumbingProduct().neighbor(direction);

        if(neighbor!= null){

            boolean flag = getLastFillingPlumbingProduct().isCanFilled(neighbor);

            if(flag){
                nextPlumbingProduct(neighbor);
            }
            else{
                result = false;
            }
        }
        else{
            result = false;
        }

        return result;
    }

    private void stop(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStopped){
            stop();
        }
        else {
            flow();
        }
    }

    // ---------------------- геттеры -------------------
    public int timeout(){
        return timeout;
    }
    
    

    //------  Работа со слушателями------------------------

    //TODO    !!!
    List<FlowActionListener> FlowActionListeners = new ArrayList<>();

    // присоединяет слушателя
    public void addFlowActionListener(FlowActionListener l) {
        FlowActionListeners.add(l);
    }

    // отсоединяет слушателя
    public void removeFlowActionListener(FlowActionListener l) {
        if (FlowActionListeners.contains(l)) {
            FlowActionListeners.remove(l);
        }
    }

    // оповещает слушателей о событии
    protected void fireFlowAction() {
        for (FlowActionListener FlowActionListener : FlowActionListeners) {
            FlowActionListener.flowStopped(new FlowActionEvent(this));
        }
    }
}
