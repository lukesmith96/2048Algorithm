package com.company;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import static com.company.Tile.TILE_SIZE;

/**
 * Created by Luke Smith on 08-Jul-17.
 */

public class Board implements Cloneable {

    public static final int BOARD_WIDTH = 4;
    public static final int BOARD_HEIGHT = 4;

    public IntegerProperty score;
    private boolean hasMoved;
    private Tile[][] grid;


    public Board(Tile[][] grid) {
        this.grid = grid;
        score = new SimpleIntegerProperty();
    }

    @Override
    public Board clone() {
        Tile[][] newGrid = new Tile[BOARD_HEIGHT][BOARD_WIDTH];
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                newGrid[i][j] = (grid[i][j] == null)? null : grid[i][j].clone();
            }
        }
        return new Board(newGrid);
    }

    public Board() {
        this.grid = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                grid[x][y] = null;
            }
        }
        createTile(true);
        createTile(true);
        score = new SimpleIntegerProperty();
    }

    public int move(Direction d) {
        int moveScore = 0;
        hasMoved = false;
        int y, x;
        switch (d) {
            case UP:
                // check from 1st place to last y pos
                for(y = 1; y < BOARD_HEIGHT; y++) {
                    for (x = 0; x < BOARD_WIDTH; x++) {
                        if (grid[x][y] != null) {
                            Tile blocker = canMove(Direction.UP, grid[x][y]);
                            int yToMove = (blocker != null) ? blocker.getPosY() + 1 : 0;
                            if (grid[x][y].canCollide(blocker) && !grid[x][y].isAlreadyCollided() && !blocker.isAlreadyCollided()) {
                                moveScore = collide(grid[x][y], blocker);
                                hasMoved = true;
                            } else if (yToMove != y) {
                                moveTile(x, yToMove, grid[x][y]);
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
            case DOWN:
                for(y = BOARD_HEIGHT - 2; y >= 0 ; y--) {
                    for (x = 0; x < BOARD_WIDTH; x++) {
                        if (grid[x][y] != null) {
                            Tile blocker = canMove(Direction.DOWN, grid[x][y]);
                            int yToMove = (blocker != null) ? blocker.getPosY() - 1 : 3;
                            if (grid[x][y].canCollide(blocker) && !grid[x][y].isAlreadyCollided() && !blocker.isAlreadyCollided()) {
                                moveScore = collide(grid[x][y], blocker);
                                hasMoved = true;
                            } else if (yToMove != y) {
                                moveTile(x, yToMove, grid[x][y]);
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
            case LEFT:
                for(x = 1; x < BOARD_WIDTH; x++) {
                    for (y = 0; y < BOARD_HEIGHT; y++) {
                        if (grid[x][y] != null) {
                            Tile blocker = canMove(Direction.LEFT, grid[x][y]);
                            int xToMove = (blocker != null) ? blocker.getPosX() + 1 : 0;
                            if (grid[x][y].canCollide(blocker) && !grid[x][y].isAlreadyCollided() && !blocker.isAlreadyCollided()) {
                                moveScore = collide(grid[x][y], blocker);
                                hasMoved = true;
                            } else if (xToMove != x) {
                                moveTile(xToMove, y, grid[x][y]);
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
            case RIGHT:
                for(x = BOARD_WIDTH - 2; x >= 0; x--) {
                    for (y = 0; y < BOARD_HEIGHT; y++) {
                        if (grid[x][y] != null) {
                            Tile blocker = canMove(Direction.RIGHT, grid[x][y]);
                            int xToMove = (blocker != null) ? blocker.getPosX() - 1 : 3;
                            if (grid[x][y].canCollide(blocker) && !grid[x][y].isAlreadyCollided() && !blocker.isAlreadyCollided()) {
                                moveScore = collide(grid[x][y], blocker);
                                hasMoved = true;
                            } else if (xToMove != x) {
                                moveTile(xToMove, y, grid[x][y]);
                                hasMoved = true;
                            }
                        }
                    }
                }
                break;
        }
        for (x = 0; x < BOARD_WIDTH; x++)
            for (y = 0; y < BOARD_HEIGHT; y++)
                if (grid[x][y] != null)
                    grid[x][y].setCollided(false);
        return (hasMoved)? moveScore : -1;
    }

    private void moveTile(int newx, int newy, Tile tile) {
        grid[tile.getPosX()][tile.getPosY()] = null;
        tile.move(newx, newy);
        grid[newx][newy] = tile;
    }

    private int collide(Tile tile, Tile blocker) {
        Tile newTile = new Tile(blocker.getPosX(), blocker.getPosY(), blocker.getWeight() * 2);
        newTile.setCollided(true);
        grid[blocker.getPosX()][blocker.getPosY()] = newTile;
        grid[tile.getPosX()][tile.getPosY()] = null;
        //updateScore(newTile.getWeight());
        return newTile.getWeight();
    }

    public void updateScore(int value){
        score.set(score.add(value).get());
    }

    private Tile canMove(Direction d, Tile curr) {
        Tile toReturn = null;
        switch (d) {
            case UP:
                for (int y = curr.getPosY() - 1; y >= 0; y--){
                    if (grid[curr.getPosX()][y] != null)
                        toReturn = (toReturn == null)? grid[curr.getPosX()][y] : toReturn;
                }
                break;
            case DOWN:
                for (int y = curr.getPosY() + 1; y < BOARD_HEIGHT; y++){
                    if (grid[curr.getPosX()][y] != null)
                        toReturn = (toReturn == null)? grid[curr.getPosX()][y] : toReturn;
                }
                break;
            case LEFT:
                for (int x = curr.getPosX() - 1; x >= 0; x--){
                    if (grid[x][curr.getPosY()] != null)
                        toReturn = (toReturn == null)? grid[x][curr.getPosY()] : toReturn;
                }
                break;
            case RIGHT:
                for (int x = curr.getPosX() + 1; x < BOARD_WIDTH; x++){
                    if (grid[x][curr.getPosY()] != null)
                        toReturn = (toReturn == null)? grid[x][curr.getPosY()] : toReturn;
                }
                break;
        }
        return toReturn;
    }

    public Tile createTile(boolean lock) {
        Random rand = new Random();
        int randX = rand.nextInt(BOARD_WIDTH);
        int randY = rand.nextInt(BOARD_HEIGHT);
        if(grid[randX][randY] != null) {
            return createTile(lock);
        }
        int randWeight = rand.nextInt(5);
        Tile tile = new Tile(randX, randY, (randWeight == 4 && !lock) ? 4 : 2);
        grid[randX][randY] = tile;
        return tile;
    }

    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile[] row : grid)
            for (Tile tile : row)
                if (tile != null)
                    tiles.add(tile);
        return tiles;
    }

    //TODO check is valid move exists on table.
    public boolean hasValidMove() {
        if (size() < (BOARD_WIDTH * BOARD_HEIGHT))
            return true;
        boolean canMove = false;
        for(int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                if (x > 0)
                    //check left
                    canMove = (canMove)? canMove : grid[x][y].canCollide(grid[x-1][y]);
                if (y > 0)
                    //check up
                    canMove = (canMove)? canMove : grid[x][y].canCollide(grid[x][y-1]);
                if (x < BOARD_WIDTH - 1)
                    // check right
                    canMove = (canMove)? canMove : grid[x][y].canCollide(grid[x+1][y]);
                if (y < BOARD_HEIGHT - 1)
                    // check down
                    canMove = (canMove)? canMove : grid[x][y].canCollide(grid[x][y+1]);
            }
        }
        return canMove;
    }

    public int size() {
        int size = 0;
        for (Tile[] row : grid)
            for (Tile tile : row)
                if(tile!=null)size++;
        return size;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public Tile[][] cloneGrid() {
        Tile[][] newGrid = new Tile[BOARD_HEIGHT][BOARD_WIDTH];
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                newGrid[i][j] = (grid[i][j] == null)? null : grid[i][j].clone();
            }
        }
        return newGrid;
    }
}