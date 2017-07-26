package com.company;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.*;


public class Main extends Application {

    private Board board = new Board();
    private Pane gameview = new Pane();

    public Main() throws Exception {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = createUI();
        root.setPrefSize(700, 600);
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

        pane.setOnKeyPressed(new KeyListener(board, gameview));
        gameview.setStyle("-fx-border-width:7px;");
        gameview.getChildren().addAll(board.getBackground());
        gameview.getChildren().addAll(board.getTiles());
        pane.setCenter(gameview);

        return pane;
    }
    private Label setCoordinates(Label label, double x, double y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }
}
