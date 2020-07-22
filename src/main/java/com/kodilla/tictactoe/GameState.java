package com.kodilla.tictactoe;

public class GameState {

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

    private GameState(Content content) {
        this.content = content;
    }

    public boolean checkState() {
        for (Combo combo : new CombosList(content.getBoard().getTiles()).getCombos()) {
            if (combo.isComplete()) {
                content.playWinAnimation(combo);
                return false;
            }
        }
        return true;
    }

    public Content getContent() {
        return content;
    }
}

