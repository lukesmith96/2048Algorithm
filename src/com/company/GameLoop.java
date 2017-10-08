package com.company;

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
        Function function = controller.getCurrentFunction();
        DeterministicDFSService ddfs = new DeterministicDFSService();
        while(true) {
            Direction nextDir = null;
            switch (function){
                case RANDOM:
                    nextDir = randomNextMove();
                    break;
                case CORNER:
                    nextDir = getNextMoveCornerPriority();
                    break;
                case DDFS:
                    int[] set = ddfs.getBestMove(context.getBoard().clone(), 1000);
                    nextDir = Direction.getDir(set[1]);
                    break;
                case LOOKAHEAD:
                    int[] sets = ddfs.lookAheadDDFS(context.getBoard().clone(), 4);
                    nextDir = Direction.getDir(sets[1]);
                    break;
            }
            if ((context.getBoard().size() == (Board.BOARD_SIZE * Board.BOARD_SIZE) && !context.getBoard().hasValidMove()) || nextDir == null) {
                System.out.println("You LOST!");
                break;
            }
            dirController.move(nextDir);
            context.updateUI();
            try {
                int delay = controller.getDelay();
                Thread.sleep(delay);
            } catch (Exception e) {
                break;
            }
        }
    }

    /*
     * Basic algorithm for using the popular corner priority scheme showing it's
     * in effectiveness without any other additional strategy
     */
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

    private Direction randomNextMove() {
        int pick = new Random().nextInt(Direction.values().length);
        return Direction.values()[pick];
    }
}