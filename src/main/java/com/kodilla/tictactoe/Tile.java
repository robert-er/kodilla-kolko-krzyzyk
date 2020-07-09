package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    private static boolean turnX = false;
    private static boolean playable = true;
    private boolean isSelected = false;
    private final ImageView imageX = new ImageView(new Image("file:src/main/resources/x-small.png"));
    private final ImageView imageO = new ImageView(new Image("file:src/main/resources/o-small.png"));
    Game.type type;
    static Content content = new Content();

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
                playable = content.checkState();
                isSelected = true;
                //debug
                System.out.println("turnX= " + turnX);
                System.out.println("isSelected= " + isSelected);
                System.out.println("playable= " + playable);
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                if (turnX)
                    return;
                drawO();
                turnX = true;
                playable = content.checkState();
                isSelected = true;
                //debug
                System.out.println("turnX= " + turnX);
                System.out.println("isSelected= " + isSelected);
                System.out.println("playable= " + playable);
            }

        });
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

    public static boolean isPlayable() {
        return playable;
    }

    public static Content getContent() {
        return content;
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
