package com.company;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * Created by lukes on 08-Jul-1 7.
 */
public class UIController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (((Button)event.getSource()).getText().equals("Random Direction")){
            System.out.println("YOU CLICKED RANDOM BUTTON");
        }
    }
}
