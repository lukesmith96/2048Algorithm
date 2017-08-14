package com.company;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.Random;

/**
 * Created by lukes on 02-Aug-17.
 */
public class GameLoop extends Thread {
    Main.GameContext context;
    DirectionController dirController;
    UIController controller;
    Direction prevDirection = Direction.DOWN;
    public GameLoop(Main.GameContext context, UIController controller){
        this.context = context;
        dirController = new DirectionController(context);
        this.controller = controller;
    }

    @Override
    public void run(){
        boolean running = true;
        Function function = controller.getCurrentFunction();
        DeterministicDFSService ddfs = new DeterministicDFSService();
        while(running) {
            Direction nextDir = null;
            switch (function){
                case RANDOM:
                    nextDir = callRandom();
                    break;
                case STACK:
                    nextDir = getNextMoveStackPriority();
                    break;
                case CORNER:
                    nextDir = getNextMoveCornerPriority();
                    break;
                case DDFS:
                    nextDir = ddfs.getBestMove(context.getBoard().clone(), 1000);
                    break;
            }
            if (nextDir == null){
                //ToDo you lost!
                running = false;
                break;
            }
            dirController.move(nextDir);
            context.updateUI();
            if (context.getBoard().size() == 16 && !context.getBoard().hasValidMove()) {
                System.out.println("You LOST!");
                break;
            }
            try {
                int delay = controller.getDelay();
                Thread.sleep(delay);
            } catch (Exception e) {
                running = false;
            }
        }
    }

    private Direction getNextMoveStackPriority() {

        return Direction.LEFT;
    }

    private Direction getNextMoveCornerPriority() {
        Random rand = new Random();
        boolean right = context.getBoard().hasValidMove(Direction.RIGHT);
        boolean down = context.getBoard().hasValidMove(Direction.DOWN);
        int randomInt = rand.nextInt(2);
        if (prevDirection == Direction.UP) {
            prevDirection = Direction.DOWN;
            return  Direction.DOWN;
        }
        else if (prevDirection == Direction.LEFT){
            prevDirection = Direction.RIGHT;
            return Direction.RIGHT;
        }
        if (!right && !down){
            if (randomInt == 0){
                prevDirection = Direction.LEFT;
                return Direction.LEFT;
            }
            else {
                prevDirection = Direction.UP;
                return Direction.UP;
            }

        }
        if(randomInt == 0){
            if (right && prevDirection == Direction.DOWN){
                return Direction.RIGHT;
            }else{
                if (prevDirection == Direction.DOWN)
                    return Direction.DOWN;
                return Direction.RIGHT;
            }
        }else{
            if (down && prevDirection == Direction.RIGHT){
                return Direction.DOWN;
            }else{
                if (prevDirection == Direction.RIGHT)
                    return Direction.RIGHT;
                return Direction.DOWN;
            }
        }
    }

    private Direction callRandom() {
        int pick = new Random().nextInt(Direction.values().length);
        return Direction.values()[pick];
    }
}
