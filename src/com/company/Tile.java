package com.company;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        rect.setFill(Color.web("#EEE4DA"));
        relocate(x * TILE_SIZE + TILE_BORDER, y * TILE_SIZE + TILE_BORDER);
        Text text = new Text(weight + "");
        text.setFont(new Font(40));
        text.setStyle("-fx-font-weight: bold");
        text.setFill(new Color(0,0,0, 1));
        this.getChildren().addAll(rect, text);
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
        System.out.println("Pos X:" + posX + " Y:" + posY);
        relocate(posX * TILE_SIZE + TILE_BORDER, posY * TILE_SIZE + TILE_BORDER);
    }
}
