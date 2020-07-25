package com.kodilla.tictactoe;

import java.io.Serializable;

public class Save implements Serializable {

    private int scoresWonGames;
    private int scoresLostGames;
    private Game.type[][] board = new Game.type[3][3];

    public Game.type[][] getBoard() {
        return board;
    }

    public void setBoard() {
        Tile[][] tiles = GameState.getGameState().getContent().getBoard().getTiles();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[j][i] = tiles[j][i].getValue();
            }
        }
    }

    public int getScoresWonGames() {
        return scoresWonGames;
    }

    public void setScoresWonGames(int scoresWonGames) {
        this.scoresWonGames = scoresWonGames;
    }

    public int getScoresLostGames() {
        return scoresLostGames;
    }

    public void setScoresLostGames(int scoresLostGames) {
        this.scoresLostGames = scoresLostGames;
    }
}
