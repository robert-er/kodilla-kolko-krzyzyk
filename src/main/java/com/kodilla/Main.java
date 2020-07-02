package com.kodilla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{

    private final Image imageback = new Image("file:src/main/resources/kolko-krzyzyk-tlo.jpg");
    private final Image signO = new Image("file:src/main/resources/o-small.png");
    private final Image signX = new Image("file:src/main/resources/x-small.png");
    private FlowPane signsO = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane signsO1 = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane signsX = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane signsX1 = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 1, 1, 10));
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setBackground(background);

        ImageView imgO = new ImageView(signO);
        ImageView imgX = new ImageView(signX);
        ImageView imgO1 = new ImageView(signO);
        ImageView imgX1 = new ImageView(signX);
        signsO.getChildren().add(imgO);
        signsX.getChildren().add(imgX);
        signsO1.getChildren().add(imgO1);
        signsX1.getChildren().add(imgX1);
        grid.add(signsO, 0, 0, 1, 1);
        grid.add(signsX,0,1,1,1);
        grid.add(signsO1, 0, 3, 1, 1);
        grid.add(signsX1,1, 0, 1, 1);

        Scene scene = new Scene(grid, 600, 600, Color.BLACK);

        primaryStage.setTitle("kółko krzyżyk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
