package com.kodilla.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.List;


public class Menu {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;

    private static Label difficultyMenu = new Label(Tile.difficulty);

    public static void setDifficultyMenu(String labelName) {
        difficultyMenu.setText(labelName);
    }

    private List<Pair<Label, Runnable>> menuData = Arrays.asList(
            new Pair<Label, Runnable>(new Label("NOWA GRA"), Game::newGame),
            new Pair<Label, Runnable>(difficultyMenu, Tile::changeDifficulty),
            new Pair<Label, Runnable>(new Label("ZAPISZ GR\u0118"), () -> {}),
            new Pair<Label, Runnable>(new Label("WCZYTAJ GR\u0118"), () -> {}),
            new Pair<Label, Runnable>(new Label("ZAMKNIJ MENU"), () -> {}),
            new Pair<Label, Runnable>(new Label("WYJ\u015aCIE Z GRY"), Platform::exit)
    );

    public Menu() {
        setBackground();
        addTitle();
        root.setPrefSize(WIDTH, HEIGHT);

        double lineX = WIDTH / 2 - 200;
        double lineY = HEIGHT / 2 - 200;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);
    }

    public void setBackground() {
        Image imageBg = new Image("file:src/main/resources/background-table-3.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true,
                true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y);
        line.setStrokeWidth(5);
        line.setStroke(Color.WHITE);
        line.setEffect(new DropShadow(5, Color.BLACK));
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                new KeyValue(line.endXProperty(), line.getStartX()),
                new KeyValue(line.endYProperty(), line.getStartY() + 350)));
        timeLine.play();

        root.getChildren().add(line);
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey().getText());
            item.setOnAction(data.getValue());
            item.setTranslateX(0);

            Rectangle clip = new Rectangle(400, 50);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

    private void addTitle() {
        MenuTitle title = new MenuTitle("K\u00f3\u0142ko i krzy\u017cyk");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3 - 130);

        root.getChildren().add(title);
    }

    public Pane getRoot() {
        return root;
    }

}
