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
        if (event.getCode() == KeyCode.UP && UIController.currFunction == Function.USER) {
            dirControl.move(Direction.UP);
        }
        if (event.getCode() == KeyCode.DOWN && UIController.currFunction == Function.USER) {
            dirControl.move(Direction.DOWN);
        }
        if (event.getCode() == KeyCode.LEFT && UIController.currFunction == Function.USER) {
            dirControl.move(Direction.LEFT);
        }
        if (event.getCode() == KeyCode.RIGHT && UIController.currFunction == Function.USER) {
            dirControl.move(Direction.RIGHT);
        }
        if (event.getCode() == KeyCode.SPACE)
            UIController.running = false;
        event.consume();
    }
}
