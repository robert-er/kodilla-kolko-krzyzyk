package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) {
        Content content = new Content();
        GameState.init(content);

        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(content.getRoot()));
        primaryStage.show();
    }

    enum type {
        X, O, EMPTY
    }

    public static void main(String[] args) {
        launch(args);
    }
}