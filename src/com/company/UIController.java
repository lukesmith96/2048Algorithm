package com.company;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;

import static javafx.scene.input.KeyCode.*;

/**
 * Created by lukes on 08-Jul-1 7.
 */
public class UIController implements EventHandler<ActionEvent> {

    public static int delay;
    public static boolean RANDOM, CORNER, STACK, MIX, USER;
    private Slider msSlider;
    public UIController(){
        disableControllers();
        delay = 51;
    }

    private void disableControllers() {
        RANDOM = false;
        CORNER = false;
        STACK = false;
        MIX = false;
        USER = false;
        if(msSlider != null)
            msSlider.setDisable(false);
    }

    @Override
    public void handle(ActionEvent event) {
        if (((Button)event.getSource()).getText().equals("Random")){
            System.out.println("YOU CLICKED RANDOM BUTTON");
            disableControllers();
            RANDOM = true;
        }
        if (((Button)event.getSource()).getText().equals("User Control")){
            System.out.println("YOU CLICKED USER");
            disableControllers();
            msSlider.setDisable(true);
            USER = true;
        }
        if (((Button)event.getSource()).getText().equals("Corner Priority")){
            disableControllers();
            CORNER = true;
        }
        if (((Button)event.getSource()).getText().equals("Stack Priority")){
            disableControllers();
            STACK = true;
        }
        if (((Button)event.getSource()).getText().equals("Mix Priority")){
            disableControllers();
            MIX = true;
        }
    }

    public  void setSliderRef(Slider msSlider){
        this.msSlider = msSlider;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
