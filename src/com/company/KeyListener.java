package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

/**
 * Created by lukes on 16-Jul-17.
 */
public class KeyListener implements EventHandler<KeyEvent> {
    public Board board;
    public Pane gameview;
    public DirectionController dirControl;

    public KeyListener(Board board, Pane gameview) {
        this.board = board;
        this.gameview = gameview;
        dirControl = new DirectionController(board, gameview);
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP && UIController.USER == true) {
            dirControl.move(Direction.UP);
        }
        if (event.getCode() == KeyCode.DOWN && UIController.USER == true) {
            dirControl.move(Direction.DOWN);
        }
        if (event.getCode() == KeyCode.LEFT && UIController.USER == true) {
            dirControl.move(Direction.LEFT);
        }
        if (event.getCode() == KeyCode.RIGHT && UIController.USER == true) {
            dirControl.move(Direction.RIGHT);
        }
        event.consume();
    }
}
