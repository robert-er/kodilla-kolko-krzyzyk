package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Game extends Application {

    private Content content = new Content();
    private Menu menu = new Menu();
    private Menu scores = new Menu();

    private Scene menuScene = new Scene(menu.getRoot());
    private Scene gameScene = new Scene(content.getRoot());
    private Scene scoreScene = new Scene(scores.getRoot());

    private Button btnBackToMenu = new Button("MENU");

    SaveGame saveGame = new SaveGame();

    @Override
    public void start(Stage primaryStage) {
        GameState.init(content);
        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);

        Text wonGames = new Text(""+Scores.getWonGames());
        wonGames.setTranslateX(336);
        wonGames.setTranslateY(187);
        wonGames.setFont(Font.font("Verdana", 20));
        wonGames.setFill(Color.WHITE);

        Text lostGames = new Text(""+Scores.getLostGames());
        lostGames.setTranslateX(336);
        lostGames.setTranslateY(247);
        lostGames.setFont(Font.font("Verdana", 20));
        lostGames.setFill(Color.WHITE);

        menu.addMenuData(new Pair<>(new Label("NOWA GRA"), () -> {
            content = new Content();
            GameState.newGame(content);
            Tile.setDefaultValues();
            CombosList.setDefaultValues();
            Minimax.setDefaultValues();
            Computer.setDefaultValues();
            gameScene = new Scene(content.getRoot());
            content.getRoot().getChildren().add(btnBackToMenu);
            primaryStage.setScene(gameScene);
        }
        ));

        Text difficultyLevel = new Text(Tile.getDifficulty());
        difficultyLevel.setTranslateX(336);
        difficultyLevel.setTranslateY(187);
        difficultyLevel.setFont(Font.font("Verdana", 20));
        difficultyLevel.setFill(Color.WHITE);

        menu.addMenuData(new Pair<>(new Label("POZIOM TRUDNO\u015aCI"), () -> {
            Tile.changeDifficulty();
            difficultyLevel.setText(Tile.getDifficulty());
        }));
        menu.addMenuData(new Pair<>(new Label("ZAPISZ GR\u0118"), () -> {
            saveGame.save();
        }));
        menu.addMenuData(new Pair<>(new Label("WCZYTAJ GR\u0118"), () -> {
            saveGame.load();
        }));
        menu.addMenuData(new Pair<>(new Label("STATYSTYKI"), () -> {
            wonGames.setText(""+Scores.getWonGames());
            lostGames.setText(""+Scores.getLostGames());
            primaryStage.setScene(scoreScene);
        }));
        menu.addMenuData(new Pair<>(new Label("ZAMKNIJ MENU"), () -> primaryStage.setScene(gameScene)));
        menu.addMenuData(new Pair<>(new Label("WYJ\u015aCIE Z GRY"), Platform::exit));

        menu.generate();
        menu.getRoot().getChildren().add(difficultyLevel);

        btnBackToMenu.setOnAction(e -> primaryStage.setScene(menuScene));
        content.getRoot().getChildren().add(btnBackToMenu);

        scores.addMenuData(new Pair<>(new Label("          STATYSTYKI"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("WYGRANE"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("PRZEGRANE"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("RESETUJ STATYSTYKI"), () -> {
            Scores.reset();
            wonGames.setText(""+Scores.getWonGames());
            lostGames.setText(""+Scores.getLostGames());
        }));
        scores.addMenuData(new Pair<>(new Label(""), () -> {}));
        scores.addMenuData(new Pair<>(new Label(""), () -> {}));
        scores.addMenuData(new Pair<>(new Label("POWR\u00d3T DO MENU"), () -> primaryStage.setScene(menuScene)));

        scores.generate();
        scores.getRoot().getChildren().addAll(wonGames, lostGames);

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