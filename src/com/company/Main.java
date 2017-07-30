package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.StringConverter;

import java.util.concurrent.TimeUnit;


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
        root.setPrefSize(720, 630);
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
        algorithmList.setId("xbox");
        algorithmList.setPrefSize(250,300);
        algorithmList.setAlignment(Pos.BASELINE_CENTER);

        UIController buttonController = new UIController();
        Button randomButton = new Button("Random");
        randomButton.setOnAction(buttonController);

        Button s1Button = new Button("Corner Priority");
        s1Button.setOnAction(buttonController);

        Button s2Button = new Button("Stack Priority");
        s2Button.setOnAction(buttonController);

        Button s3Button = new Button("Mix Priority");
        s3Button.setOnAction(buttonController);

        Button userControlButton = new Button("User");
        userControlButton.setOnAction(buttonController);

        algorithmList.getChildren().addAll(randomButton, s1Button, s2Button, s3Button, userControlButton);
        pane.setRight(algorithmList);

        Pane sPane = new Pane();
        pane.setPadding(new Insets(0,0,0,10));
        Slider msSlider = new Slider();
        msSlider.setMin(1);
        msSlider.setMax(301);
        msSlider.setValue(51);
        msSlider.setMajorTickUnit(50);
        msSlider.setShowTickLabels(true);
        msSlider.setShowTickMarks(true);
        StringConverter<Double> stringConverter = new StringConverter<Double>() {

            @Override
            public String toString(Double d) {
                int i = d.intValue();
                if (i == 1)
                    return d.intValue() + "ms";
                else return d.intValue() + "ms";
            }

            @Override
            public Double fromString(String string) {
                System.out.println("Hello");
                return 5.0;
            }
        };
        msSlider.setLabelFormatter(stringConverter);
        Label label = new Label();
        Popup popup = new Popup();
        popup.getContent().add(label);
        msSlider.setPrefWidth(490);
        msSlider.setOnMouseDragged(e -> {
            int val = (int)Math.round(msSlider.getValue());
            label.setText(val + "ms");
            popup.show(msSlider, e.getScreenX(), sPane.getLayoutY() + 10);
        });
        msSlider.setOnMouseExited(e -> popup.hide());
        msSlider.setOnMouseDragExited(e -> popup.hide());
        msSlider.setOnMouseDragReleased(e -> popup.hide());
        msSlider.setOnMouseReleased(e -> popup.hide());
        sPane.getChildren().add(msSlider);
        pane.setBottom(sPane);

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
