package com.company;


import javafx.application.Platform;
import javafx.scene.layout.Pane;

enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT
}
/**
 * Created by lukes on 30-Jul-17.
 */
public class DirectionController {
    public Main.GameContext gameContext;

    public DirectionController(Main.GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public void move(Direction d){
        System.out.println("Going Direction: " + d.toString());
        switch (d){
        case DOWN:
            Platform.runLater(() -> {
                if (gameContext.getBoard().makeMove(Direction.DOWN, gameContext)) {
                    gameContext.addTile(gameContext.getBoard().createTile(false));
                }
            });
            break;
        case UP:
            Platform.runLater(() -> {
                if(gameContext.getBoard().makeMove(Direction.UP, gameContext))
                    gameContext.addTile(gameContext.getBoard().createTile(false));
            });
            break;
        case RIGHT:
            Platform.runLater(() -> {
                if (gameContext.getBoard().makeMove(Direction.RIGHT, gameContext)) {
                    gameContext.addTile(gameContext.getBoard().createTile(false));
                }
            });
            break;
        case LEFT:
            Platform.runLater(() -> {
                if (gameContext.getBoard().makeMove(Direction.LEFT, gameContext)) {
                    gameContext.addTile(gameContext.getBoard().createTile(false ));
                }
            });
            break;
        }
        if (gameContext.getBoard().size() == 16 && !gameContext.getBoard().isValidMove()) {
            System.out.println("You LOST!");
        }
    }
}
