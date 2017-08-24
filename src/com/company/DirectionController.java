package com.company;


enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT;

    public static Direction getDir(int value) {
        switch (value) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return RIGHT;
            case 3:
                return LEFT;
        }
        return null;
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
        int mvScore;
        if ((mvScore = gameContext.getBoard().move(d)) >= 0) {
            gameContext.updateScore(mvScore);
            gameContext.getBoard().createTile(false);
        }

    }
}
