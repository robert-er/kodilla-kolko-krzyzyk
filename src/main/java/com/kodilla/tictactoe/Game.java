package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    private static Content content = new Content();
    private Menu menu = new Menu();

    private static Stage menuStage = new Stage();
    private static Stage gameStage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        GameState.init(content);
        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);


        primaryStage.setScene(new Scene(menu.getRoot()));
        primaryStage.show();
        System.out.println("primaryStage.setScene(new Scene(menu.getRoot()));");

        refreshStage();

//            primaryStage.setScene(new Scene(content.getRoot()));
//            System.out.println("primaryStage.setScene(new Scene(content.getRoot()));");
//            primaryStage.show();

    }

    public static void newGame() {
        GameState.init(content);
        gameStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        gameStage.setResizable(false);
        gameStage.getScene();
        gameStage.close();
        gameStage.setScene(new Scene(content.getRoot()));
        gameStage.show();

    }

    public static void refreshStage() {

    }

    enum type {
        X, O, EMPTY
    }

    public static void main(String[] args) {
        launch(args);
    }
}