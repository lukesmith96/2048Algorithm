package com.company;

import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.*;
import com.company.Tile;

import static com.company.Tile.TILE_BOARDER;
import static com.company.Tile.TILE_SIZE;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = createUI();
        root.setPrefSize(600, 400);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/com/company/UIStyle.css");
        primaryStage.setTitle("2048 Algorithms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane createUI() {
        BorderPane pane = new BorderPane();
        Pane top = new Pane();
        top.setPrefSize(600,90);
        Label header = new Label("2048 Algorithms Visualizer");
        header.setId("label-title");
        header = setCoordinates(header, 108, 0);
        Label by = new Label("By:");
        by.setId("label-by");
        by = setCoordinates(by, 130, 43);
        Label author = new Label("Luke Smith");
        author = setCoordinates(author, 160, 43);
        top.getChildren().addAll(header, by, author);
        pane.setTop(top);

        VBox algorithmList = new VBox();
        algorithmList.setPrefSize(165,300);
        algorithmList.setAlignment(Pos.BASELINE_CENTER);
        Button randomButton = new Button("Random Direction");
        algorithmList.getChildren().addAll(randomButton);
        pane.setRight(algorithmList);

        Pane gameview = new Pane();
        gameview.setStyle("-fx-border-width:7px;");
        Board board = new Board();
        //Rectangle border = new Rectangle(TILE_SIZE * 3 + (2 * TILE_BOARDER), TILE_SIZE * 3 + (2 * TILE_BOARDER));
        //border.setStroke(new Color(187,173,160, 1));
        gameview.getChildren().addAll(board.getBackground(), board.getTiles());
        pane.setCenter(gameview);

        return pane;
    }

    private Label setCoordinates(Label label, double x, double y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }
}
