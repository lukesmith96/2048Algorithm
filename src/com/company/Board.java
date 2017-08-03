package com.company;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import static com.company.Tile.TILE_SIZE;

/**
 * Created by Luke Smith on 08-Jul-17.
 */

public class Board {

    public static final int BOARD_WIDTH = 4;
    public static final int BOARD_HEIGHT = 4;
    public static final Image defaultImage = new Image("/com/company/defaultSquare.jpg");
    private static int score;
    private Group background = new Group();
    private ArrayList<Tile> tiles = new ArrayList<>();

    Board() {
        for (int x =0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++){
                Rectangle backg = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                ImagePattern pat = new ImagePattern(defaultImage);
                backg.setFill(pat);
                background.getChildren().add(backg);
            }
        }
        tiles.add(createTile(true));
        tiles.add(createTile(true));
        score = 0;
    }

    public boolean makeMove(Direction d, Main.GameContext gameContext) {
        boolean hasMoved = false;
        int y, x;
        switch (d) {
            case UP:
                for(y = 1; y < BOARD_HEIGHT; y++) {
                    ArrayList<Tile> temp = (ArrayList<Tile>)tiles.clone();
                    for (Tile curr : temp){
                        if (curr.getPosY() == y) {
                            Tile block = canMove(Direction.UP, curr);
                            int yToMove = (block!=null)? block.getPosY() + 1 : 0;
                            if (block != null && curr.canCollide(block)) {
                                switchblocks(gameContext, curr, block);
                                hasMoved = true;
                            }
                            else if (yToMove != curr.getPosY()) {
                                curr.move(curr.getPosX(), yToMove);
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
            case DOWN:
                for(y = 2; y >= 0; y--) {
                    ArrayList<Tile> temp = (ArrayList<Tile>)tiles.clone();
                    for (Tile curr : temp){
                        if (curr.getPosY() == y) {
                            Tile block = canMove(Direction.DOWN, curr);
                            int yToMove = (block!=null)? block.getPosY() - 1 : 3;
                            if (block != null && curr.canCollide(block)){
                                switchblocks(gameContext,curr,block);
                                hasMoved = true;
                            }
                            else if (yToMove != curr.getPosY()) {
                                curr.move(curr.getPosX(), yToMove);
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
            case LEFT:
                for(x = 1; x < BOARD_WIDTH; x++) {
                    ArrayList<Tile> temp = (ArrayList<Tile>)tiles.clone();
                    for (Tile curr : temp){
                        if (curr.getPosX() == x) {
                            Tile block = canMove(Direction.LEFT, curr);
                            int xToMove = (block!=null)? block.getPosX() + 1 : 0;
                            if (block != null && curr.canCollide(block)){
                                switchblocks(gameContext,curr,block);
                                hasMoved = true;
                            }
                            else if (xToMove != curr.getPosX()) {
                                curr.move(xToMove, curr.getPosY());
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
            case RIGHT:
                for(x = 2; x >= 0; x--) {
                    ArrayList<Tile> temp = (ArrayList<Tile>)tiles.clone();
                    for (Tile curr : temp){
                        if (curr.getPosX() == x) {
                            Tile block = canMove(Direction.RIGHT, curr);
                            int xToMove = (block!=null)? block.getPosX() - 1 : 3;
                            if (block != null && curr.canCollide(block)) {
                                switchblocks(gameContext, curr, block);
                                hasMoved = true;
                            }
                            else if(xToMove != curr.getPosX()) {
                                curr.move(xToMove, curr.getPosY());
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
        }
        return hasMoved;
    }

    private void switchblocks(Main.GameContext gameContext, Tile curr, Tile block) {
        Tile newTile = curr.collide(block);
        gameContext.removeTile(curr);
        gameContext.removeTile(block);
        gameContext.addTile(newTile);
        score += newTile.getWeight();
        System.out.println("Score: " + score);
    }

    private Tile canMove(Direction d, Tile curr) {
        Tile toReturn = null;
        switch (d) {
            case UP:
                for (Tile t : tiles) {
                    if (t.getPosY() < curr.getPosY()  && t.getPosX() == curr.getPosX()) {
                         toReturn = ((((toReturn != null)? toReturn.getPosY() : -1) < t.getPosY()) || toReturn == null)? t : toReturn;
                    }
                }
                break;
            case DOWN:
                for (Tile t : tiles) {
                    if (t.getPosY() > curr.getPosY()  && t.getPosX() == curr.getPosX()) {
                        toReturn = ((((toReturn != null)? toReturn.getPosY() : 4) > t.getPosY()) || toReturn == null)? t : toReturn;
                    }
                }
                break;
            case LEFT:
                for (Tile t : tiles) {
                    if (t.getPosX() < curr.getPosX()  && t.getPosY() == curr.getPosY()) {
                        toReturn = ((((toReturn != null)? toReturn.getPosX() : -1) < t.getPosX()) || toReturn == null)? t : toReturn;
                    }
                }
                break;
            case RIGHT:
                for (Tile t : tiles) {
                    if (t.getPosX() > curr.getPosX()  && t.getPosY() == curr.getPosY()) {
                        toReturn = ((((toReturn != null)? toReturn.getPosX() : 4) > t.getPosX()) || toReturn == null)? t : toReturn;
                    }
                }
                break;
        }
        return toReturn;
    }

    public Tile createTile(boolean lock) {
        Random rand = new Random();
        int randX = rand.nextInt(4);
        int randY = rand.nextInt(4);
        for (Tile tile : tiles) {
            if (tile.getPosX() == randX && tile.getPosY() == randY)
                return createTile(lock);
        }
        int randWeight = rand.nextInt(5);
        Tile tile = new Tile(randX, randY, (randWeight == 4 && !lock) ? 4 : 2);
        return tile;
    }

    public Group getBackground() {return background; }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void add(Tile tile) {
        tiles.add(tile);
    }

    //TODO check is valid move exists on table.
    public boolean isValidMove() {
        return false;
    }

    public void removeTile(Tile tileToRemove) {
        tiles.remove(tileToRemove);
    }
    public int size(){
        return tiles.size();
    }
}