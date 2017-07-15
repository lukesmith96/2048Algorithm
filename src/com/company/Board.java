package com.company;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static com.company.Tile.TILE_BOARDER;
import static com.company.Tile.TILE_SIZE;

/**
 * Created by lukes on 08-Jul-17.
 */
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
        Tile tile = new Tile(1,1);
        tiles.getChildren().addAll(tile);
    }
    public Group getBackground() {return background; }
    public Group getTiles() {
        return tiles;
    }
}
