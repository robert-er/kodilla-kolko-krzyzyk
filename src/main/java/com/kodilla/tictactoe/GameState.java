package com.kodilla.tictactoe;

public class GameState {

    public static GameState gameState;

    private Content content;

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
                if (combo.getTile(0).getValue() == Game.type.X) {
                    Scores.won();
                }
                if (combo.getTile(0).getValue() == Game.type.O) {
                    Scores.lost();
                }
                System.out.println("won=" + Scores.getWonGames() + " lost=" + Scores.getLostGames());
                return false;
            }
        }
        return true;
    }

    public Content getContent() {
        return content;
    }

}

