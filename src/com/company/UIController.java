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
    DDFS,
    USER
}
/**
 * Created by lukes on 08-Jul-1 7.
 */
public class UIController implements EventHandler<ActionEvent> {

    public static int delay;
    public static Function currFunction;
    public static DirectionController dirController;
    private Main.GameContext gameContext;
    private GameLoop loop;
    private Slider msSlider;

    public UIController(Main.GameContext gameContext){
        disableControllers();
        delay = 51;
        dirController = new DirectionController(gameContext);
        currFunction = USER;
        this.gameContext = gameContext;
        loop = new GameLoop(gameContext, this);
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
        if (((Button) event.getSource()).getText().equals("Random")) {
            disableControllers();
            currFunction = RANDOM;
            loop = new GameLoop(gameContext, this);
            loop.start();
        }
        if (((Button) event.getSource()).getText().equals("User Control")) {
            disableControllers();
            msSlider.setDisable(true);
            currFunction = USER;
        }
        if (((Button) event.getSource()).getText().equals("Corner Priority")) {
            disableControllers();
            currFunction = CORNER;
            loop = new GameLoop(gameContext, this);
            loop.start();
        }
        if (((Button) event.getSource()).getText().equals("Stack Priority")) {
            disableControllers();
            currFunction = STACK;
            loop = new GameLoop(gameContext, this);
            loop.start();
        }
        if (((Button) event.getSource()).getText().equals("Deterministic DFS")) {
            disableControllers();
            currFunction = DDFS;
            loop = new GameLoop(gameContext, this);
            loop.start();
        }
    }
    public int getDelay(){
        return delay;
    }

    public  void setSliderRef(Slider msSlider){
        this.msSlider = msSlider;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Function getCurrentFunction() {
        return currFunction;
    }
}
