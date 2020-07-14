package com.kodilla.tictactoe;

import java.util.concurrent.TimeUnit;

public class Computer {

    private static GameState gameState = GameState.getGameState();
    private static Content content = gameState.getContent();
    private static Tile[][] board = content.getBoard().getTiles();

    public static void playComputer() throws InterruptedException {
        System.out.println("Computer.playComputer");
        System.out.println("Tile.isComputerTurn()=" + Tile.isComputerTurn()
                + " Tile.isTurnX()=" + Tile.isTurnX());
        Tile.setComputerTurn(true);
        if (Tile.isPlayable()) {
            for (Tile[] tile : board) {
                for (Tile tile1 : tile) {
                    if (!tile1.isSelected()) {
                        tile1.playComputer();
                        break;
                    }
                }
            }
        }
    }


}
