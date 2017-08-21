package com.company;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Luke Smith on 09-Jul-17.
 */

public class Tile implements Cloneable {

    public static final int TILE_SIZE = 121;
    public static final int TILE_BORDER = 7;
    private int posX, posY, weight;
    private boolean collided;

    public Tile(int x, int y, int weight){
        posX = x;
        posY = y;
        this.weight = weight;
    }
    public StackPane updateTileUI(){
        Rectangle rect = new Rectangle(0,0, TILE_SIZE - (TILE_BORDER * 2),TILE_SIZE - (TILE_BORDER * 2));
        rect.setFill(getColor(weight));
        Text text = new Text(weight + "");
        text.setFont(new Font(40));
        text.setStyle("-fx-font-weight: bold");
        text.setFill(new Color(0,0,0, 1));
        StackPane pane = new StackPane(rect,  text);
        pane.relocate(this.posX * TILE_SIZE + TILE_BORDER, this.posY * TILE_SIZE + TILE_BORDER);
        return pane;
    }
    private Paint getColor(int weight) {
        String[] colors = {"#EEE4DA", "#EDE0C8", "#F2B179",
                "#F59563", "#F67C5F", "#F65E3B", "#EDCF72",
                "#EDCC61", "#EDC850", "#EDC53F", "#EDC22E",
                "#3C3A32"};
        int pick = (int)(Math.log(weight) / Math.log(2)) - 1;
        return Color.web(colors[pick]);
    }

    @Override
    public Tile clone(){
        try {
            return (Tile)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getWeight(){
        return weight;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean canCollide(Tile block) {
        if(block != null && this.getWeight() == block.getWeight())
            return true;
        return false;
    }

    public void move(int newX, int newY) {
        this.posX = newX;
        this.posY = newY;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public boolean isAlreadyCollided() {
        return collided;
    }
}
