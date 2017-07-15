package com.company;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.Random;

import static com.company.Tile.TILE_BOARDER;
import static com.company.Tile.TILE_SIZE;

/**
 * Created by Luke Smith on 08-Jul-17.
 */
enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT;
}
public class Board {

    public static final int BOARD_WIDTH = 4;
    public static final int BOARD_HEIGHT = 4;

    public static final Image defaultImage = new Image("/com/company/defaultSquare.jpg");
    private Group background = new Group();
    private Group tiles = new Group();

    Board() {
        for (int x =0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++){
                Rectangle backg = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                ImagePattern pat = new ImagePattern(defaultImage);
                backg.setFill(pat);
                background.getChildren().add(backg);
            }
        }
        /*Random rand = new Random();
        int randX = rand.nextInt(4);
        int randY = rand.nextInt(4);
        Tile tile = new Tile(randX,randY,2);
        randX = rand.nextInt(4);
        randY = rand.nextInt(4);
        Tile tile2 = new Tile(randX, randY,2);*/
        tiles.getChildren().addAll(createTile(true), createTile(true));
    }
    public void makeMove(Direction d) {
        switch (d) {
            case UP:

                break;
            case DOWN:

                break;
            case LEFT:

                break;
            case RIGHT:

                break;
        }
    }
    public Tile createTile(boolean lock) {
        Random rand = new Random();
        int randX = rand.nextInt(4);
        int randY = rand.nextInt(4);
        for (Node n : tiles.getChildren()) {
            Tile tile = (Tile)n;
            if(tile.getPosX() == randX && tile.getPosY() == randY)
                return createTile(lock);
        }
        int randWeight = rand.nextInt(4);
        Tile tile = new Tile(randX,randY, (randWeight == 4 && !lock)? 4 : 2);
        return tile;
    }
    public Group getBackground() {return background; }
    public Group getTiles() {
        return tiles;
    }
}
