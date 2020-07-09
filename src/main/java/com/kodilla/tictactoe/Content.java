package com.kodilla.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Content {
    private Tile[][] board = new Tile[3][3];
    private Pane root = new Pane();
    private static CombosList combos = new CombosList();

    public Pane createContent() {
        Image imageBg = new Image("file:src/main/resources/background.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setPrefSize(600, 600);
        root.setBackground(background);

        //create tiles panel 3x3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(15 + (200 * j));
                tile.setTranslateY(15 + (200 * i));

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        //create winning combos list
        //horizontal
        for (int y = 0; y < 3; y++) {
            combos.addCombo(board[0][y], board[1][y], board[2][y]);
        }
        //vertical
        for (int x = 0; x < 3; x++) {
            combos.addCombo(board[x][0], board[x][1], board[x][2]);
        }
        //diagonal
        combos.addCombo(board[0][0], board[1][1], board[2][2]);
        combos.addCombo(board[2][0], board[1][1], board[0][2]);

        return root;
    }

    public boolean checkState() {
        for (Combo combo : combos.getCombos()) {
            if (combo.isComplete()) {
                playWinAnimation(combo);
                return false;
            }
        }
       return true;
    }

    private void playWinAnimation(Combo combo) {
        Line line = new Line();
        line.setStrokeWidth(10);
        line.setStroke(Color.RED);
        line.setStartX(combo.getTile(0).getCenterX());
        line.setStartY(combo.getTile(0).getCenterY());
        line.setEndX(combo.getTile(0).getCenterX());
        line.setEndY(combo.getTile(0).getCenterY());
        System.out.println("playWinAnimation");
        System.out.println("combo.getTile(0).getCenterX()" + combo.getTile(0).getCenterX());
        System.out.println("combo.getTile(0).getCenterY()" + combo.getTile(0).getCenterY());
        System.out.println("combo.getTile(2).getCenterX()" + combo.getTile(2).getCenterX());
        System.out.println("combo.getTile(2).getCenterY()" + combo.getTile(2).getCenterY());
        root.getChildren().add(line);
        System.out.println("root contains line ? " + root.getChildren().contains(line));
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), combo.getTile(2).getCenterX()),
                new KeyValue(line.endYProperty(), combo.getTile(2).getCenterY())));
        timeLine.play();

    }
}
