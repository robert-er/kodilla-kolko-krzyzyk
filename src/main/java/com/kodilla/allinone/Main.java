package com.kodilla.allinone;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private boolean playable = true;
    private boolean turnX = true;
    private Tile[][] board = new Tile[3][3];
    private List<Combo> combos = new ArrayList<>();

    private Pane root = new Pane();

    private Parent createContent() {
        Image imageBg = new Image("file:src/main/resources/background.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setPrefSize(600, 600);
        root.setBackground(background);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(15 + (200 * j));
                tile.setTranslateY(15 + (200 * i));

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }
        //horizontal
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }

        //vertical
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }

        //diagonal
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("K\u00f3\u0142ko i krzy\u017cyk");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private void checkState() {
        for (Combo combo : combos) {
            if (combo.isComplete()) {
                playable = false;
                playWinAnimation(combo);
                break;
            }
        }
    }

    private void playWinAnimation(Combo combo) {
        Line line = new Line();
        line.setStrokeWidth(10);
        line.setStroke(Color.RED);
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[0].getCenterX());
        line.setEndY(combo.tiles[0].getCenterY());
        root.getChildren().add(line);
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
        timeLine.play();
    }

    private class Combo {
        private Tile[] tiles;

        public Combo(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty())
                return false;
            return tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[0].getValue().equals(tiles[2].getValue());
        }
    }

    private class Tile extends StackPane {
        private final Text text = new Text();
        private boolean isSelected = false;
        private final ImageView imageX = new ImageView(new Image("file:src/main/resources/x-small.png"));
        private final ImageView imageO = new ImageView(new Image("file:src/main/resources/o-small.png"));

        public Tile() {
            Rectangle border = new Rectangle(170, 180);
            border.setFill(null);
            border.setStroke(Color.WHITE);
            border.setVisible(false);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (!playable || isSelected)
                    return;

                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!turnX)
                        return;
                    drawX();
                    turnX = false;
                    checkState();
                    isSelected = true;
                }
                else if (event.getButton() == MouseButton.SECONDARY) {
                    if (turnX)
                        return;
                    drawO();
                    turnX = true;
                    checkState();
                    isSelected = true;
                }
            });
        }

        public String getValue() {
            return text.getText();
        }

        public double getCenterX() {
            return getTranslateX() + 85;
        }

        public double getCenterY() {
            return getTranslateY() + 90;
        }

        private void drawX() {
            getChildren().add(imageX);
            text.setText("X");
        }

        private void drawO() {
            getChildren().add(imageO);
            text.setText("O");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
