package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
/**
 * Created by lukes on 16-Jul-17.
 */
public class KeyListener implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            board.makeMove(Direction.UP);
        }
    }
}
