package com.company;


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
        switch (d){
        case DOWN:
            if (gameContext.getBoard().makeMove(Direction.DOWN, gameContext)) {
                gameContext.addTile(gameContext.getBoard().createTile(false));
            }
            break;
        case UP:
            if (gameContext.getBoard().makeMove(Direction.UP, gameContext)) {

                gameContext.addTile(gameContext.getBoard().createTile(false));
            }
            break;
        case RIGHT:
            if (gameContext.getBoard().makeMove(Direction.RIGHT, gameContext)) {
                gameContext.addTile(gameContext.getBoard().createTile(false));
            }
            break;
        case LEFT:
            if(gameContext.getBoard().makeMove(Direction.LEFT, gameContext)) {
                gameContext.addTile(gameContext.getBoard().createTile(false));
            }
            break;
        }
        if (gameContext.getBoard().size() == 16 && !gameContext.getBoard().isValidMove()) {
            System.out.println("You LOST!");
        }
    }
}
