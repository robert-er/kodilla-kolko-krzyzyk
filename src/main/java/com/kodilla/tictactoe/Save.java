package com.kodilla.tictactoe;

import java.io.Serializable;

public class Save implements Serializable {
    private int scoresWonGames;
    private int scoresLostGames;

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
