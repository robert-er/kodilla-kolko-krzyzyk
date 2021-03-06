package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    private static boolean difficultyIsHard = false;
    private static String difficulty = "\u0141ATWY";
    private static boolean turnX = true;
    private static boolean computerTurn = false;
    private static boolean playable = true;
    private boolean isSelected = false;
    private final ImageView imageX = new ImageView(new Image("file:src/main/resources/x-small.png"));
    private final ImageView imageO = new ImageView(new Image("file:src/main/resources/o-small.png"));
    private Game.Type value;

    private int x, y;

    public Tile() {
        Rectangle border = new Rectangle(170, 180);
        border.setFill(null);
        border.setStroke(Color.WHITE);
        border.setVisible(false);

        setAlignment(Pos.CENTER);
        getChildren().add(border);
        value = Game.Type.EMPTY;
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
                computerTurn = true;
                playable = GameState.getGameState().checkState();

                if (difficultyIsHard) {
                    Computer.playAdvancedAI();
                } else {
                    Computer.playSimpleAI();
                }
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                if (turnX)
                    return;
                drawO();
                turnX = true;
                playable = GameState.getGameState().checkState();
            }

        });
    }

    public static void changeDifficulty() {
        difficultyIsHard = !difficultyIsHard;
        if (difficultyIsHard) {
            difficulty = "TRUDNY";
        } else {
            difficulty = "\u0141ATWY";
        }
        System.out.println("Tile.difficultyIsHard=" + difficultyIsHard + " " + difficulty);
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public void playComputer() {
        System.out.println("Tile.playComputer. CenterX=" + getCenterX() + " CenterY=" + getCenterY()
                + " x=" + x + " y=" + y);
        System.out.println("turnX=" + turnX + " computerTurn=" + computerTurn);
        if (computerTurn) {
            if (turnX)
                return;
            drawO();
            turnX = true;
            playable = GameState.getGameState().checkState();
            isSelected = true;
            computerTurn = false;
            System.out.println("turnX=" + turnX + " computerTurn=" + computerTurn);
        }
    }

    public Game.Type getValue() {
        return value;
    }

    public void setValue(Game.Type value) {
        this.value = value;
    }

    public double getCenterX() {
        return getTranslateX() + 85;
    }

    public double getCenterY() {
        return getTranslateY() + 90;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public static boolean isPlayable() {
        return playable;
    }

    public static void setPlayable(boolean playable) {
        Tile.playable = playable;
    }

    public void drawX() {
        getChildren().add(imageX);
        value = Game.Type.X;
        isSelected = true;
    }

    public void drawO() {
        getChildren().add(imageO);
        value = Game.Type.O;
        isSelected = true;
    }

    public static void setDefaultValues() {
        turnX = true;
        computerTurn = false;
        playable = true;
    }

}
