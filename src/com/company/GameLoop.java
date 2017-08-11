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
                    break;
                case CORNER:
                    break;
                case DDFS:
                    nextDir = ddfs.getBestMove(context.getBoard().clone(), 1000);
                    break;
            }
            if (nextDir == null){
                //ToDo you lost!
                break;
            }
            dirController.move(nextDir);
            context.updateUI();
            try {
                int delay = controller.getDelay();
                Thread.sleep(delay);
            } catch (Exception e) {
                running = false;
            }
        }
    }

    private Direction callRandom() {
        int pick = new Random().nextInt(Direction.values().length);
        return Direction.values()[pick];
    }
}
