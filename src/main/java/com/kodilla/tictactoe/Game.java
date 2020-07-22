package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Game extends Application {

    private Content content = new Content();
    private Menu menu = new Menu();

    private Stage menuStage = new Stage();
    private Stage gameStage = new Stage();

    private Scene menuScene = new Scene(menu.getRoot());
    private Scene gameScene = new Scene(content.getRoot());

    private Button btnExitMenu= new Button("ZAMKNIJ MENU - BTN Z KLASY GAME ");
    private Button btnBackToMenu = new Button("MENU - BTN Z KLASY GAME");

    @Override
    public void start(Stage primaryStage) {
        GameState.init(content);
        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);
        btnExitMenu.setTranslateX(100);
        btnExitMenu.setTranslateY(300);
        btnExitMenu.setOnAction(e -> primaryStage.setScene(gameScene));
        btnBackToMenu.setOnAction(e -> primaryStage.setScene(menuScene));
        menu.getRoot().getChildren().add(btnExitMenu);
        content.getRoot().getChildren().add(btnBackToMenu);
        primaryStage.setScene(menuScene);
        primaryStage.show();
        System.out.println("primaryStage.setScene(new Scene(menu.getRoot()));");

//            primaryStage.setScene(new Scene(content.getRoot()));
//            System.out.println("primaryStage.setScene(new Scene(content.getRoot()));");
//            primaryStage.show();

    }

    public void newGame() {
        GameState.init(content);
        gameStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        gameStage.setResizable(false);
        gameStage.getScene();
        gameStage.close();
        gameStage.setScene(new Scene(content.getRoot()));
        gameStage.show();

    }

    enum type {
        X, O, EMPTY
    }

    public static void main(String[] args) {
        launch(args);
    }
}