package com.kodilla.tictactoe;

import java.io.Serializable;

public class Save implements Serializable {

    private int scoresWonGames;
    private int scoresLostGames;
    private int scoresDrawGames;
    private Game.Type[][] board = new Game.Type[3][3];

    public Game.Type[][] getBoard() {
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

    public int getScoresDrawGames() {
        return scoresDrawGames;
    }

    public void setScoresDrawGames(int scoresDrawGames) {
        this.scoresDrawGames = scoresDrawGames;
    }
}
