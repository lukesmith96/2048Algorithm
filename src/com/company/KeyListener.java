package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

/**
 * Created by lukes on 16-Jul-17.
 */
public class KeyListener implements EventHandler<KeyEvent> {
    public DirectionController dirControl;
    private Main.GameContext gameContext;

    public KeyListener(Main.GameContext gameContext) {
        this.gameContext = gameContext;
        dirControl = new DirectionController(gameContext);
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
