package com.company;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Luke Smith on 09-Jul-17.
 */

public class Tile extends StackPane {

    public static final int TILE_SIZE = 121;
    public static final int TILE_BORDER = 7;
    public static final Image defaultImage = new Image("/com/company/defaultSquare.jpg");
    private int posX, posY, weight;
    public Tile(int x, int y, int weight){
        posX = x;
        posY = y;
        this.weight = weight;
        Rectangle rect = new Rectangle(x * TILE_SIZE + TILE_BORDER,y * TILE_SIZE + TILE_BORDER,
                TILE_SIZE - (TILE_BORDER * 2),TILE_SIZE - (TILE_BORDER * 2));
        rect.setFill(getColor(weight));
        relocate(x * TILE_SIZE + TILE_BORDER, y * TILE_SIZE + TILE_BORDER);
        Text text = new Text(weight + "");
        text.setFont(new Font(40));
        text.setStyle("-fx-font-weight: bold");
        text.setFill(new Color(0,0,0, 1));
        this.getChildren().addAll(rect, text);
    }

    private Paint getColor(int weight) {
        String[] colors = {"#EEE4DA", "#EDE0C8", "#F2B179",
                "#F59563", "#F67C5F", "#F65E3B", "#EDCF72",
                "#EDCC61", "#EDC850", "#EDC53F", "#EDC22E",
                "#3C3A32"};
        int pick = (int)(Math.log(weight) / Math.log(2)) - 1;
        return Color.web(colors[pick]);
    }

    public Tile collide(Tile tile) {
        return new Tile(tile.getPosX(), tile.getPosY(), weight * 2);
    }

    public int getWeight(){
        return weight;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean canCollide(Tile block) {
        if(this.getWeight() == block.getWeight())
            return true;
        else
            return false;
    }

    public void move(int newX, int newY) {
        this.posX = newX;
        this.posY = newY;
        relocate(posX * TILE_SIZE + TILE_BORDER, posY * TILE_SIZE + TILE_BORDER);
    }
}
