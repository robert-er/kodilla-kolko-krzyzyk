package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

public class Tile extends StackPane {

    private static boolean turnX = true;
    private static boolean computerTurn = false;
    private static boolean playable = true;
    private boolean isSelected = false;
    private final ImageView imageX = new ImageView(new Image("file:src/main/resources/x-small.png"));
    private final ImageView imageO = new ImageView(new Image("file:src/main/resources/o-small.png"));
    private Game.type type;

    public Tile() {
        Rectangle border = new Rectangle(170, 180);
        border.setFill(null);
        border.setStroke(Color.WHITE);
        border.setVisible(false);

        setAlignment(Pos.CENTER);
        getChildren().add(border);
        onClick();
    }

    private void onClick() {
        setOnMouseClicked(event -> {
            if (isSelected || !playable)
                return;

            if (event.getButton() == MouseButton.PRIMARY) {
                if (!turnX)
                    return;
                drawX();
                turnX = false;
                playable = GameState.getGameState().checkState();
                isSelected = true;
                try {
                    Computer.playComputer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                if (turnX)
                    return;
                drawO();
                turnX = true;
                playable = GameState.getGameState().checkState();
                isSelected = true;
            }

        });
    }

    public void playComputer() throws InterruptedException {
        System.out.println("Tile.playComputer");
        if (computerTurn) {
            if (turnX)
                return;
            Thread.sleep(200);
            drawO();
            turnX = true;
            playable = GameState.getGameState().checkState();
            isSelected = true;
            computerTurn = false;
        }
    }

    public Game.type getValue() {
        return type;
    }

    public double getCenterX() {
        return getTranslateX() + 85;
    }

    public double getCenterY() {
        return getTranslateY() + 90;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public static boolean isTurnX() {
        return turnX;
    }

    public static boolean isComputerTurn() {
        return computerTurn;
    }

    public static boolean isPlayable() {
        return playable;
    }

    public static void setComputerTurn(boolean computerTurn) {
        Tile.computerTurn = computerTurn;
    }

    private void drawX() {
        getChildren().add(imageX);
        type = Game.type.X;
    }

    private void drawO() {
        getChildren().add(imageO);
        type = Game.type.O;
    }
}
