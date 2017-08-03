package com.company;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.util.Random;

import static com.company.Function.*;


enum Function{
    RANDOM,
    CORNER,
    STACK,
    MIX,
    USER
}
/**
 * Created by lukes on 08-Jul-1 7.
 */
public class UIController implements EventHandler<ActionEvent> {

    public static int delay;
    public static Function currFunction;
    public static DirectionController dirController;
    public static boolean running = false;
    //public Task loop;
    public Thread thread;
    private Slider msSlider;
    public UIController(Main.GameContext gameContext){
        disableControllers();
        delay = 51;
        dirController = new DirectionController(gameContext);
        currFunction = Function.USER;
    }

    private void disableControllers() {
        if (thread != null)
            thread.interrupt();
        if(msSlider != null)
            msSlider.setDisable(false);
    }

    @Override
    public void handle(ActionEvent event) {
        if (((Button)event.getSource()).getText().equals("Random")){
            disableControllers();
            /*
            running = true;
            while(running){
                randomRun();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Ran Random at delay: " + delay);
            }
            //loop = new RunLoop(delay, this, RANDOM);
            //thread = new Thread(loop);
            //thread.start();*/
        }
        if (((Button)event.getSource()).getText().equals("User Control")){
            System.out.println("YOU CLICKED USER");
            disableControllers();
            msSlider.setDisable(true);
        }
        if (((Button)event.getSource()).getText().equals("Corner Priority")){
            disableControllers();
        }
        if (((Button)event.getSource()).getText().equals("Stack Priority")){
            disableControllers();
        }
        if (((Button)event.getSource()).getText().equals("Mix Priority")){
            disableControllers();
        }
    }

    /*private class RunLoop extends Task<Void> {
        boolean running = true;
        Function function;
        int delay;
        UIController con;
        public RunLoop(int delay, UIController controller, Function function){
            this.delay = delay;
            con = controller;
            this.function = function;
        }
        @Override
        public Void call() {
            System.out.print("Call thread!!!!!");
            while (running) {
                System.out.println("Run! " + delay + " Function: " + function);
                switch (function) {
                    case RANDOM:
                        con.randomRun();
                        break;
                    case CORNER:
                        break;
                    case MIX:
                        break;
                    case STACK:
                        break;
                }
                System.out.print("Hello?");
                try {
                    delay = con.getDelay();
                    System.out.println("Delay " + delay);
                    //this.wait(delay);
                    Thread.sleep(100);
                    System.out.println("Thread Complete");
                } catch (InterruptedException e) {
                    System.out.println("INTERRUPT!");
                    running = false;
                }
            }
            return null;
        }
    }*/

    public int getDelay(){
        return delay;
    }
    public void randomRun(){
        int pick = new Random().nextInt(Direction.values().length);
        Direction curr = Direction.values()[pick];
        dirController.move(curr);
    }
    public  void setSliderRef(Slider msSlider){
        this.msSlider = msSlider;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
