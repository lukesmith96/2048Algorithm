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
    private Main.GameContext gameContext;
    private GameLoop loop;
    private Slider msSlider;
    public UIController(Main.GameContext gameContext){
        disableControllers();
        delay = 51;
        dirController = new DirectionController(gameContext);
        currFunction = USER;
        this.gameContext = gameContext;
    }

    private void disableControllers() {
        if (loop != null) {
            loop.interrupt();
            System.out.println("Disabling loop");
        }
        if(msSlider != null)
            msSlider.setDisable(false);
    }

    @Override
    public void handle(ActionEvent event) {
        if (((Button)event.getSource()).getText().equals("Random")){
            disableControllers();
            loop = new GameLoop(gameContext, this);
            loop.start();
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
