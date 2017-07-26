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
            board.makeMove(Direction.UP, gameview);
            try {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            } catch (Exception e) {
                System.out.println("You LOST! " + e.getMessage());
            }
        }
        if (event.getCode() == KeyCode.DOWN) {
            board.makeMove(Direction.DOWN, gameview);
            try {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            } catch (Exception e) {
                System.out.println("You LOST! " + e.getMessage());
            }
        }
        if (event.getCode() == KeyCode.LEFT) {
            board.makeMove(Direction.LEFT, gameview);
            try {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            } catch (Exception e) {
                System.out.println("You LOST! " + e.getMessage());
            }
        }
        if (event.getCode() == KeyCode.RIGHT) {
            board.makeMove(Direction.RIGHT, gameview);
            try {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            } catch (Exception e) {
                System.out.println("You LOST! " + e.getMessage());
            }
        }
    }
}
