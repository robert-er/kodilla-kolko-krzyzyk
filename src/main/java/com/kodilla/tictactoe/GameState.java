package com.kodilla.tictactoe;

import java.io.Serializable;

public class GameState implements Serializable {

    public static GameState GAME_STATE;

    private Content content;

    public static GameState init(Content content) {
        if (GAME_STATE == null) {
            GAME_STATE = new GameState(content);
        }
        return GAME_STATE;
    }

    public static GameState newGame(Content content) {
        GAME_STATE = new GameState(content);
        return GAME_STATE;
    }

    public static GameState getGameState() {
        return GAME_STATE;
    }

    public static void setGameState(GameState gameState) {
        GAME_STATE = gameState;
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

