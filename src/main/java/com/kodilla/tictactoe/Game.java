package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Game extends Application {

    private Content content = new Content();
    private Menu menu = new Menu();

    private Scene menuScene = new Scene(menu.getRoot());
    private Scene gameScene = new Scene(content.getRoot());

    private Button btnExitMenu= new Button("ZAMKNIJ MENU - BTN Z KLASY GAME ");
    private Button btnChangeDifficulty = new Button(Tile.getDifficulty());
    private Button btnBackToMenu = new Button("MENU - BTN Z KLASY GAME");

    @Override
    public void start(Stage primaryStage) {
        GameState.init(content);
        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);

        btnExitMenu.setTranslateX(100);
        btnExitMenu.setTranslateY(300);
        btnExitMenu.setOnAction(e -> primaryStage.setScene(gameScene));
        menu.getRoot().getChildren().add(btnExitMenu);

        btnChangeDifficulty.setTranslateX(100);
        btnChangeDifficulty.setTranslateY(150);
        btnChangeDifficulty.setOnAction(e -> {
            Tile.changeDifficulty();
            btnChangeDifficulty.setText(Tile.getDifficulty());
        });
        menu.getRoot().getChildren().add(btnChangeDifficulty);

        btnBackToMenu.setOnAction(e -> primaryStage.setScene(menuScene));
        content.getRoot().getChildren().add(btnBackToMenu);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    enum type {
        X, O, EMPTY
    }

    public static void main(String[] args) {
        launch(args);
    }
}