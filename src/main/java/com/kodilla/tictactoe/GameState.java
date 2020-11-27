package com.kodilla.tictactoe;

public class GameState {

    public static GameState gameState;

    private Content content;
    private static boolean gameWasWon;
    private static boolean gameWasLost;
    private static boolean gameWasDraw;

    public static void init(Content content) {
        if (gameState == null) {
            gameState = new GameState(content);
        }
    }

    public static void newGame(Content content) {
        gameState = new GameState(content);
    }

    public static GameState getGameState() {
        return gameState;
    }

    private GameState(Content content) {
        this.content = content;
    }

    public boolean checkState() {
        for (Combo combo : new CombosList(content.getBoard().getTiles()).getCombos()) {
            if (combo.isComplete()) {
                content.playWinAnimation(combo);
                if (combo.getTile(0).getValue() == Game.Type.X) {
                    Scores.won();
                    setGameStatusFlags(true, false, false);
                }
                if (combo.getTile(0).getValue() == Game.Type.O) {
                    Scores.lost();
                    setGameStatusFlags(false, true, false);
                }
                System.out.println("won=" + Scores.getWonGames() + " lost=" + Scores.getLostGames());
                return false;
            }

            if (!Minimax.hasFreeTiles() && !combo.isComplete()) {
                Scores.draw();
                setGameStatusFlags(false, false, true);
                break;
            }
        }
        return true;
    }

    public static void setGameStatusFlags(boolean gameWasWon, boolean gameWasLost, boolean gameWasDraw) {
        GameState.gameWasWon = gameWasWon;
        GameState.gameWasLost = gameWasLost;
        GameState.gameWasDraw = gameWasDraw;
    }

    public Content getContent() {
        return content;
    }

    public static boolean isGameWasWon() {
        return gameWasWon;
    }

    public static boolean isGameWasLost() {
        return gameWasLost;
    }

    public static boolean isGameWasDraw() {
        return gameWasDraw;
    }

}

