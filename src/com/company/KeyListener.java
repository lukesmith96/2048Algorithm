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
            if (board.makeMove(Direction.UP, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            if (!board.isValidMove()) {
                System.out.println("You LOST! ");
            }
        }
        if (event.getCode() == KeyCode.DOWN) {
            if (board.makeMove(Direction.DOWN, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            if (!board.isValidMove()) {
                System.out.println("You LOST! ");
            }
        }
        if (event.getCode() == KeyCode.LEFT) {
            if(board.makeMove(Direction.LEFT, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            if (!board.isValidMove()) {
                System.out.println("You LOST! ");
            }
        }
        if (event.getCode() == KeyCode.RIGHT) {
            if (board.makeMove(Direction.RIGHT, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            if (!board.isValidMove()) {
                System.out.println("You LOST! ");
            }
        }
    }
}
