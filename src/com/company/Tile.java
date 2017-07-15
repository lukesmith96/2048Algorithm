package com.company;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * Created by Luke Smith on 09-Jul-17.
 */

public class Tile extends StackPane {

    public static final int TILE_SIZE = 121;
    public static final int TILE_BOARDER = 7;
    public static final Image defaultImage = new Image("/com/company/defaultSquare.jpg");
    private int posX, posY;
    public Tile(int x, int y){
        posX = x;
        posY = y;
        Rectangle rect = new Rectangle(x * TILE_SIZE + TILE_BOARDER,y * TILE_SIZE + TILE_BOARDER,
                TILE_SIZE - (TILE_BOARDER * 2),TILE_SIZE - (TILE_BOARDER * 2));
        rect.setFill(Color.web("#EEE4DA"));
        //setHeight(TILE_SIZE - (TILE_BOARDER * 2));
        //setWidth(TILE_SIZE - (TILE_BOARDER * 2));
        relocate(x * TILE_SIZE + TILE_BOARDER, y * TILE_SIZE + TILE_BOARDER);
        Text text = new Text("2");
        text.setFont(new Font(40));
        text.setStyle("-fx-font-weight: bold");
        text.setFill(new Color(0,0,0, 1));
        this.getChildren().addAll(rect, text);
    }
    public int getWeight(){
        return 0;
    }
    public int setWeight(){
        return 0;
    }
}
