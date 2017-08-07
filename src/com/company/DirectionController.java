package com.company;


import javafx.application.Platform;
import javafx.scene.layout.Pane;

enum Direction {
    UP(0),
    DOWN(1),
    RIGHT(2),
    LEFT(3);
    private int value;

    public static Direction getDir(int value){
        switch (value) {
            case 0: return UP;
            case 1: return DOWN;
            case 2: return RIGHT;
            case 3: return LEFT;
        }
        return null;
    }
    public int getValue(){
        return value;
    }

    Direction(int value) {
        this.value = value;
    }
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
        if (gameContext.getBoard().move(d)) {
            gameContext.getBoard().createTile(false);
        }
        if (gameContext.getBoard().size() == 16 && !gameContext.getBoard().isValidMove()) {
            System.out.println("You LOST!");
        }
    }
}
