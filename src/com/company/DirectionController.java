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
    public Board board;
    public Pane gameview;

    public DirectionController(Board board, Pane gameview) {
        this.board = board;
        this.gameview = gameview;
    }

    public void move(Direction d){
        switch (d){
        case DOWN:
            if (board.makeMove(Direction.DOWN, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            break;
        case UP:
            if (board.makeMove(Direction.UP, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            break;
        case RIGHT:
            if (board.makeMove(Direction.RIGHT, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            break;
        case LEFT:
            if(board.makeMove(Direction.LEFT, gameview)) {
                Tile tile = board.createTile(false);
                board.add(tile);
                gameview.getChildren().add(tile);
            }
            break;
        }
        if (!board.isValidMove()) {
            System.out.println("You LOST! ");
        }

    }
}
