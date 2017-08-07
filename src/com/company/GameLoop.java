package com.company;

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
        System.out.println("Spawn Game Loop");
    }

    @Override
    public void run(){
        boolean running = true;
        while(running) {
            int pick = new Random().nextInt(Direction.values().length);
            Direction curr = Direction.values()[pick];
            dirController.move(curr);
            context.updateUI();
            try {
                int delay = controller.getDelay();
                System.out.println("Delay: " + delay);
                Thread.sleep(delay);
            } catch (Exception e) {
                System.out.println("INTERRUPT!");
                running = false;
            }
        }
    }
}
