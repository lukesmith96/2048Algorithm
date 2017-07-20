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

    public KeyListener(Board board, Pane gameview) {
        this.board = board;
        this.gameview = gameview;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            System.out.println("I have listened!");
            board.makeMove(Direction.UP, gameview);
            System.out.println("Adding new Tile!");
            Tile tile = board.createTile(false);
            board.add(tile);
            gameview.getChildren().add(tile);
        }
        if (event.getCode() == KeyCode.DOWN) {
            System.out.println("I have listened!");
            board.makeMove(Direction.DOWN, gameview);
            System.out.println("Adding new Tile!");
            Tile tile = board.createTile(false);
            board.add(tile);
            gameview.getChildren().add(tile);
        }
        if (event.getCode() == KeyCode.LEFT) {
            System.out.println("I have listened!");
            board.makeMove(Direction.LEFT, gameview);
            System.out.println("Adding new Tile!");
            Tile tile = board.createTile(false);
            board.add(tile);
            gameview.getChildren().add(tile);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            System.out.println("I have listened!");
            board.makeMove(Direction.RIGHT, gameview);
            System.out.println("Adding new Tile!");
            Tile tile = board.createTile(false);
            board.add(tile);
            gameview.getChildren().add(tile);
        }
    }
}
