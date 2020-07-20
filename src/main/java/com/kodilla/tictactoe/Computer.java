package com.kodilla.tictactoe;

import java.util.List;

public class Computer {

    private static Tile[][] board = GameState.getGameState().getContent().getBoard().getTiles();
    private static List<Tile> availableTiles=  Minimax.getAvailableTiles();

    public static void playComputer() {
        System.out.println("Computer.playComputer");
        Tile.setComputerTurn(true);
        if (Tile.isPlayable()) {
            for (Tile[] tile : board) {
                for (Tile tile1 : tile) {
                    if (!tile1.isSelected()) {
                        System.out.println("tile value " + tile1.getValue());
                        tile1.playComputer();
                        break;
                    }
                }
            }
        }
    }

    public static void playSimpleAI() {

        for (Tile tile : availableTiles) {
            System.out.println("x= " + tile.getCenterX() + ", y=  " + tile.getCenterY() + " , value= " + tile.getValue());
        }
        System.out.println("----------");
        if (Tile.isPlayable()) {
            for (Tile tile : availableTiles) {
                if (!tile.isSelected()) {
                    System.out.println("tile value " + tile.getValue());
                    tile.playComputer();
                    break;
                }
            }
        }
    }

    public static void playAdvancedAI() {
        if (Minimax.hasFreeTiles()) {
            int result = Minimax.minimax(0, Game.type.O);
            System.out.println("playAdvancedAI: result=" + result);
            Minimax.getComputerMove().playComputer();
        }
    }



}
