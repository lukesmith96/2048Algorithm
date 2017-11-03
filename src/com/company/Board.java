package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Luke Smith on 08-Jul-17.
 */

public class Board implements Cloneable {

    public static final int BOARD_SIZE = 4;
    private boolean hasMoved;
    private Tile[][] grid;
    public int selfScore = 0;

    /*
    * Init Board function
     */
    public Board() {
        this.grid = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                grid[x][y] = null;
            }
        }
        createTile(true);
        createTile(true);
    }

    public Board(Tile[][] grid) {
        this.grid = grid;
    }

    /*
    * Deep clone for algorithm state analysis
    */
    @Override
    public Board clone() {
        Tile[][] newGrid = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                newGrid[i][j] = (grid[i][j] == null)? null : grid[i][j].clone();
            }
        }
        return new Board(newGrid);
    }

    public int move(Direction d) {
        int moveScore = 0;
        hasMoved = false;
        int y, x;
        switch (d) {
            case UP:
                // check from 1st place to last y pos
                for(y = 1; y < BOARD_SIZE; y++) {
                    for (x = 0; x < BOARD_SIZE; x++) {
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
                for(y = BOARD_SIZE - 2; y >= 0 ; y--) {
                    for (x = 0; x < BOARD_SIZE; x++) {
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
                for(x = 1; x < BOARD_SIZE; x++) {
                    for (y = 0; y < BOARD_SIZE; y++) {
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
                for(x = BOARD_SIZE - 2; x >= 0; x--) {
                    for (y = 0; y < BOARD_SIZE; y++) {
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
        for (x = 0; x < BOARD_SIZE; x++)
            for (y = 0; y < BOARD_SIZE; y++)
                if (grid[x][y] != null)
                    grid[x][y].setCollided(false);
        return (hasMoved)? moveScore : -1;
    }

    private void moveTile(int newx, int newy, Tile tile) {
        grid[tile.getPosX()][tile.getPosY()] = null;
        tile.move(newx, newy);
        grid[newx][newy] = tile;
    }

    /*
    * @param Tile tile the current tile that is trying to move.
    * @param Tile blocker the blocking tile to collide with
    * Collides two tiles that are already verified to be
    * collidable.
    * @return the weight of the new tile for keeping score
     */
    private int collide(Tile tile, Tile blocker) {
        Tile newTile = new Tile(blocker.getPosX(), blocker.getPosY(), blocker.getWeight() * 2);
        newTile.setCollided(true);
        grid[blocker.getPosX()][blocker.getPosY()] = newTile;
        grid[tile.getPosX()][tile.getPosY()] = null;
        selfScore+=newTile.getWeight();
        return newTile.getWeight();
    }

    /*
    * @param Tile curr: the tile to evaluate
    * @param Direction d to evaluate
    *
    * returns the first tile that collides with the current tile traveling the given direction
    * if there is no tile then it can move all the way to end
    */
    private Tile canMove(Direction d, Tile curr) {
        Tile toReturn = null;
        switch (d) {
            case UP:
                for (int y = curr.getPosY() - 1; y >= 0; y--){
                    if ((toReturn = grid[curr.getPosX()][y]) != null)
                        return toReturn;
                }
                break;
            case DOWN:
                for (int y = curr.getPosY() + 1; y < BOARD_SIZE; y++){
                    if ((toReturn = grid[curr.getPosX()][y]) != null)
                        return toReturn;
                }
                break;
            case LEFT:
                for (int x = curr.getPosX() - 1; x >= 0; x--){
                    if ((toReturn = grid[x][curr.getPosY()]) != null)
                        return toReturn;
                }
                break;
            case RIGHT:
                for (int x = curr.getPosX() + 1; x < BOARD_SIZE; x++){
                    if ((toReturn = grid[x][curr.getPosY()]) != null)
                        return toReturn;
                }
                break;
        }
        return toReturn;
    }

    /*
    * @param bool lock for if creating beginning tiles and don't want to have 4
    * Creates a tile and adds to grid. 1 in 10 chance to be a 4
    * unless locked to 2.
     */
    public Tile createTile(boolean lock) {
        Random rand = new Random();
        int randX = rand.nextInt(BOARD_SIZE);
        int randY = rand.nextInt(BOARD_SIZE);
        if(grid[randX][randY] != null) {
            return createTile(lock);
        }
        int randWeight = rand.nextInt(10);
        Tile tile = new Tile(randX, randY, (randWeight == 4 && !lock) ? 4 : 2);
        grid[randX][randY] = tile;
        return tile;
    }

    public void addCustomTile(int x, int y, int value){
        Tile tile = new Tile(x, y, value);
        grid[x][y] = tile;
    }

    /*
    * Returns an ArrayList of all open spaces in current grid.
     */
    public ArrayList<Pair<Integer, Integer>> getOpenTiles() {
        ArrayList<Pair<Integer, Integer>> spaces = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (Tile[] row : grid) {
            for (Tile tile : row) {
                if (tile == null)
                    spaces.add(new Pair<>(x,y));
                ++y;
            }
            ++x;
            y = 0;
        }
        return spaces;
    }

    /*
    * Returns an ArrayList of all tiles in current grid.
     */
    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile[] row : grid)
            for (Tile tile : row)
                if (tile != null)
                    tiles.add(tile);
        return tiles;
    }

    /*
    * if the board has a valid move anywhere
     */
    public boolean hasValidMove() {
        if (size() < (BOARD_SIZE * BOARD_SIZE))
            return true;
        boolean canMove = false;
        canMove = (canMove)? canMove : hasValidMove(Direction.UP);
        canMove = (canMove)? canMove : hasValidMove(Direction.DOWN);
        canMove = (canMove)? canMove : hasValidMove(Direction.LEFT);
        canMove = (canMove)? canMove : hasValidMove(Direction.RIGHT);
        return canMove;
    }

    /*
    * @param Direction d the direction in question
    * if the Direction given contains a valid move.
    * @return boolean answer
     */
    public Boolean hasValidMove(Direction d){
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (grid[x][y] != null) {
                    Tile t = canMove(d, grid[x][y]);
                    if (t != null)
                        if (grid[x][y].canCollide(t) == true)
                            return true;
                    if (t == null) {
                        if ((x != 0 && d == Direction.LEFT) || (y != 0 && d == Direction.UP)
                                || (x != (BOARD_SIZE - 1) && d == Direction.RIGHT)
                                || (y != (BOARD_SIZE - 1) && d == Direction.DOWN))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public int size() {
        int size = 0;
        for (Tile[] row : grid)
            for (Tile tile : row)
                if(tile!=null)size++;
        return size;
    }

    public void removeTile(int x, int y) {
        grid[x][y] = null;
    }
}