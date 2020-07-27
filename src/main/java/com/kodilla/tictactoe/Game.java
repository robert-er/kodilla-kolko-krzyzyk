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
        formatMenuText(wonGames, 336, 187);

        Text lostGames = new Text(""+Scores.getLostGames());
        formatMenuText(lostGames, 336, 247);

        Text drawGames = new Text(""+Scores.getDrawGames());
        formatMenuText(drawGames, 336, 307);

        // MAIN MENU
        createMainMenu(primaryStage,wonGames, lostGames, drawGames);

        btnBackToMenu.setOnAction(e -> primaryStage.setScene(menuScene));
        content.getRoot().getChildren().add(btnBackToMenu);

        // SCORES MENU
        createScoresMenu(primaryStage, wonGames, lostGames, drawGames);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private void createMainMenu(Stage primaryStage, Text wonGames, Text lostGames, Text drawGames) {
        Text difficultyLevel = new Text(Tile.getDifficulty());
        formatMenuText(difficultyLevel, 336, 187);

        menu.addMenuData(new Pair<>(new Label("NOWA GRA"), () -> {
            createNewContent();
            primaryStage.setScene(gameScene);
        }
        ));

        menu.addMenuData(new Pair<>(new Label("POZIOM TRUDNO\u015aCI"), () -> {
            Tile.changeDifficulty();
            difficultyLevel.setText(Tile.getDifficulty());
        }));
        menu.addMenuData(new Pair<>(new Label("ZAPISZ GR\u0118"), () -> {
            saveGame.save();
        }));
        menu.addMenuData(new Pair<>(new Label("WCZYTAJ GR\u0118"), () -> {
            saveGame.load();
            createNewContent();
            content.restoreBoardAfterLoadGame();
            Tile.setPlayable(GameState.getGameState().checkState());
            if (!Minimax.hasFreeTiles()) {
                Scores.setDrawGames(Scores.getDrawGames() - 3);
            }
            if (!GameState.getGameState().checkState() && GameState.isGameWasWon()) {
                Scores.setWonGames(Scores.getWonGames() - 3);
            }
            if (!GameState.getGameState().checkState() && GameState.isGameWasLost()) {
                Scores.setLostGames(Scores.getLostGames() - 3);
            }
        }));

        menu.addMenuData(new Pair<>(new Label("STATYSTYKI"), () -> {
            wonGames.setText(""+Scores.getWonGames());
            lostGames.setText(""+Scores.getLostGames());
            drawGames.setText(""+Scores.getDrawGames());
            System.out.println("Game.start - won/lost/draw " + Scores.getWonGames() + "/"
                    + Scores.getLostGames() + "/" + Scores.getDrawGames());
            primaryStage.setScene(scoreScene);
        }));
        menu.addMenuData(new Pair<>(new Label("ZAMKNIJ MENU"), () -> primaryStage.setScene(gameScene)));
        menu.addMenuData(new Pair<>(new Label("WYJ\u015aCIE Z GRY"), Platform::exit));

        menu.generate();
        menu.getRoot().getChildren().add(difficultyLevel);
    }

    private void createScoresMenu(Stage primaryStage, Text wonGames, Text lostGames, Text drawGames) {
        scores.addMenuData(new Pair<>(new Label("          STATYSTYKI"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("WYGRANE"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("PRZEGRANE"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("REMISY"), () -> {}));
        scores.addMenuData(new Pair<>(new Label("RESETUJ STATYSTYKI"), () -> {
            Scores.reset();
            wonGames.setText(""+Scores.getWonGames());
            lostGames.setText(""+Scores.getLostGames());
            drawGames.setText(""+Scores.getDrawGames());
        }));

        scores.addMenuData(new Pair<>(new Label(""), () -> {}));
        scores.addMenuData(new Pair<>(new Label("POWR\u00d3T DO MENU"), () -> primaryStage.setScene(menuScene)));

        scores.generate();
        scores.getRoot().getChildren().addAll(wonGames, lostGames, drawGames);
    }

    private void createNewContent() {
        content = new Content();
        GameState.newGame(content);
        GameState.setGameStatusFlags(false, false, false);
        Tile.setDefaultValues();
        CombosList.setDefaultValues();
        Minimax.setDefaultValues();
        Computer.setDefaultValues();
        gameScene = new Scene(content.getRoot());
        content.getRoot().getChildren().add(btnBackToMenu);
    }

    private void formatMenuText(Text text, int x, int y) {
        text.setTranslateX(x);
        text.setTranslateY(y);
        text.setFont(Font.font("Verdana", 20));
        text.setFill(Color.WHITE);
    }

    enum Type {
        X, O, EMPTY
    }

    public static void main(String[] args) {
        launch(args);
    }

}