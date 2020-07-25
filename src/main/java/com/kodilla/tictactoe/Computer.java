package com.kodilla.tictactoe;

import java.util.List;

public class Computer {

    private static List<Tile> availableTiles =  Minimax.getAvailableTiles();

    public static void playSimpleAI() {
        for (Tile tile : availableTiles) {
            System.out.println("Computer.playSimpleAI: x= " + tile.getCenterX() + ", y=  " + tile.getCenterY() + " , value= " + tile.getValue());
        }
        System.out.println("----------");
        if (Tile.isPlayable()) {
            for (Tile tile : availableTiles) {
                if (!tile.isSelected()) {
                    System.out.println("Computer.playSimpleAI: tile value " + tile.getValue());
                    tile.playComputer();
                    break;
                }
            }
        }
    }

    public static void playAdvancedAI() {
        if (Minimax.hasFreeTiles()) {
            int result = Minimax.minimax(0, Game.type.O);
            System.out.println("Computer.playAdvancedAI: result=" + result);
            Minimax.getComputerMove().playComputer();
        }
    }

    public static void setDefaultValues() {
        availableTiles=  Minimax.getAvailableTiles();
    }

}
