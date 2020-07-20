package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Minimax {

    private static Content content = GameState.getGameState().getContent();
    private static Tile[][] board = content.getBoard().getTiles();
    private static Tile computerMove;

    public static Tile getComputerMove() {
        return computerMove;
    }

    public static List<Tile> getAvailableTiles () {
        List<Tile> availableTiles = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j< 3; j++) {
                if (board[i][j].getValue() == Game.type.EMPTY) {
                    availableTiles.add(board[i][j]);
                }
            }
        }
        return availableTiles;
    }

    public static boolean hasFreeTiles() {
        return !getAvailableTiles().isEmpty();
    }

    public static boolean hasPlayerWon(Game.type player) {
        for (Combo combo : new CombosList(content.getBoard().getTiles()).getCombos()) {
            if (combo.isComplete()) {
                if (combo.getTile(1).getValue() == player)
                    return true;
            }
        }
        return false;
    }

    public static void placeMove(Tile tile, Game.type player) {
        if (board[tile.getX()][tile.getY()].getValue() != Game.type.EMPTY)
            return;
        board[tile.getX()][tile.getY()].setValue(player);
    }

    public static int minimax(int depth, Game.type turn) {
        if (hasPlayerWon(Game.type.O))
            return 1;
        if (hasPlayerWon(Game.type.X))
            return -1;

        List<Tile> availableTiles = getAvailableTiles();

        if (availableTiles.isEmpty())
            return 0;

     //   int min = Integer.MAX_VALUE;
     //   int max = Integer.MIN_VALUE;
        int min = 10;
        int max = -10;

        for (int i = 0; i < availableTiles.size(); i++) {
            Tile tile = availableTiles.get(i);
            if (turn == Game.type.O) {
                placeMove(tile, Game.type.O);
                int currentScore = minimax(depth +1, Game.type.X);
                max = Math.max(currentScore, max);
                if (depth == 0)
                    System.out.println("Computer score for position x=" + tile.getCenterX()
                            + " y=" + tile.getCenterY() + "currentScore= " + currentScore);
                if (currentScore >=0) {
                    if (depth == 0)
                       computerMove = tile;
                }
                if (currentScore == 1) {
                    board[tile.getX()][tile.getY()].setValue(Game.type.EMPTY);
                    break;
                }
                if (i == availableTiles.size() -1 && max < 0) {
                    if (depth == 0)
                        computerMove = tile;
                }
            } else if (turn == Game.type.X) {
                placeMove(tile, Game.type.X);
                int currentScore = minimax(depth +1, Game.type.O);
                min = Math.min(currentScore, min);

                if (min == -1) {
                    board[tile.getX()][tile.getY()].setValue(Game.type.EMPTY);
                    break;
                }
            }
            board[tile.getX()][tile.getY()].setValue(Game.type.EMPTY);
        }
        return turn == Game.type.O ? max : min;
    }
}
