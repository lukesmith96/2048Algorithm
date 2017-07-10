package com.company;

import javafx.scene.Group;
import javafx.scene.Node;

/**
 * Created by lukes on 08-Jul-17.
 */
public class Board {
    public static final int TILE_SIZE = 150;
    public static final int BOARD_WIDTH = 3;
    public static final int BOARD_HIEGHT = 3;
    private Group tiles = new Group();

    Board() {
        for (int x =0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HIEGHT; y++){
                Tile tile = new Tile(x,y);
                tiles.getChildren().add(tile);
            }
        }
    }

    public Group getTiles() {
        return tiles;
    }
}
