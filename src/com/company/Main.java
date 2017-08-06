package com.company;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.StringConverter;

public class Main extends Application {

    private GameContext gameContext;
    private GameLoop gameLoop;
    private Task<Void> gameThread;
    private UIController buttonController;

    public Main() throws Exception {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create UI root
        BorderPane root = createUI();
        // set size
        root.setPrefSize(720, 630);
        //create scene and add root
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/com/company/UIStyle.css");
        // Create stage and add scene
        primaryStage.setTitle("2048 Algorithms");
        primaryStage.setScene(scene);
        // show scene
        primaryStage.show();
    }

    private BorderPane createUI() {
        gameContext = new GameContext(new Pane(), new Board());
        buttonController = new UIController(gameContext);

        // Set Top Title View
        BorderPane UI = new BorderPane();
        UI.setTop(createTopTitle());

        // Set Right button list
        VBox algorithmList = createButtonUI(buttonController);
        UI.setRight(algorithmList);

        // Set bottom slider view
        Slider msSlider = new Slider();
        buttonController.setSliderRef(msSlider);
        UI.setBottom(createSlider(msSlider, buttonController));

        // Set key listener for user control
        UI.setOnKeyPressed(new KeyListener(gameContext));

        gameContext.gameview.setStyle("-fx-border-width:7px;");
        gameContext.gameview.getChildren().addAll(gameContext.getBoard().getBackground());
        gameContext.gameview.getChildren().addAll(gameContext.getBoard().getTiles());
        UI.setCenter(gameContext.gameview);

        return UI;
    }

    private Pane createTopTitle() {
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
        return top;
    }

    private VBox createButtonUI(UIController buttonController) {
        VBox algorithmList = new VBox();
        algorithmList.setId("xbox");
        algorithmList.setPrefSize(250,300);
        algorithmList.setAlignment(Pos.BASELINE_CENTER);


        Button randomButton = new Button("Random");
        Button userControlButton = new Button("User Control");
        Button s3Button = new Button("Mix Priority");
        Button s2Button = new Button("Stack Priority");
        Button s1Button = new Button("Corner Priority");

        randomButton.setOnAction(buttonController);
        s1Button.setOnAction(buttonController);
        s2Button.setOnAction(buttonController);
        s3Button.setOnAction(buttonController);
        userControlButton.setOnAction(buttonController);

        Label scoreLabel = new Label();
        scoreLabel.setId("score");
        scoreLabel.textProperty().bind(gameContext.score.asString());
        algorithmList.getChildren().addAll(randomButton, s1Button, s2Button, s3Button, userControlButton, scoreLabel);
        return algorithmList;
    }

    private Pane createSlider(Slider msSlider, UIController buttonController) {
        Pane sPane = new Pane();
        sPane.setPadding(new Insets(0,0,0,10));
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
            buttonController.setDelay(val);
            popup.show(msSlider, e.getScreenX(), sPane.getLayoutY() + 10);
        });
        msSlider.setOnMouseExited(e -> popup.hide());
        msSlider.setOnMouseDragExited(e -> popup.hide());
        msSlider.setOnMouseDragReleased(e -> popup.hide());
        msSlider.setOnMouseReleased(e -> popup.hide());
        sPane.getChildren().add(msSlider);
        return sPane;
    }

    private Label setCoordinates(Label label, double x, double y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }
    public static class GameContext{
        private Pane gameview;
        private Board board;
        private IntegerProperty score;
        GameContext(Pane gameview, Board board){
            this.board = board;
            this.gameview = gameview;
            score = new SimpleIntegerProperty();
        }
        public Pane getGameview(){
            return gameview;
        }
        public Board getBoard() {
            return board;
        }
        public void addTile(Tile tile){
            board.add(tile);
            gameview.getChildren().add(tile);
        }
        public void removeTile(Tile tileToRemove){
            board.removeTile(tileToRemove);
            gameview.getChildren().remove(tileToRemove);
        }
        public void updateScore(int value){
            score.set(score.add(value).get());
        }
        public String getScoreString(){
            return "Score: " + score;
        }
    }
}
