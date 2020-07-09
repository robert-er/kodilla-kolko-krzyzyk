package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(Tile.getContent().createContent()));
        primaryStage.show();
    }

    enum type {
        X, O;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
