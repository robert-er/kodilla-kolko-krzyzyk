package civ6example.menu;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Civ6Title extends Pane {
    private Text text;

    public Civ6Title(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }
        Polygon bg = new Polygon(
                0, 0,
                540, 0,
                555, 30,
                540, 50,
                0, 50
        );
        bg.setStroke(Color.color(1, 1, 1, 0.75));
        bg.setEffect(new GaussianBlur());
        bg.setTranslateX(0);
        bg.setTranslateY(-40);
        text = new Text(spread);
        text.setFont(Font.font("Verdana", 44));
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(bg, text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}
