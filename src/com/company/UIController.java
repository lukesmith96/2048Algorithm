package com.company;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import static com.company.Function.*;

enum Function{
    RANDOM,
    MINIMAX,
    DDFS,
    LOOKAHEAD,
    USER
}
/**
 * Created by lukes on 08-Jul-1 7.
 */
public class UIController implements EventHandler<ActionEvent> {

    private int delay;
    public static Function currFunction;
    private Main.GameContext gameContext;
    private GameLoop loop;
    private Slider msSlider;

    public UIController(Main.GameContext gameContext){
        disableControllers();
        delay = 51;
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
        }
        if (((Button) event.getSource()).getText().equals("Minimax")) {
            disableControllers();
            currFunction = MINIMAX;
        }
        if (((Button) event.getSource()).getText().equals("Look Ahead")) {
            disableControllers();
            currFunction = LOOKAHEAD;
        }
        if (((Button) event.getSource()).getText().equals("Deterministic DFS")) {
            disableControllers();
            currFunction = DDFS;
        }
        if (((Button) event.getSource()).getText().equals("User Control")) {
            disableControllers();
            msSlider.setDisable(true);
            currFunction = USER;
        }else{
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