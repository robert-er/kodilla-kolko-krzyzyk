package com.kodilla.tictactoe;

public class Scores {

    public static int wonGames;
    private static int lostGames;

    public static void won() {
        wonGames++;
    }

    public static void lost() {
        lostGames++;
    }

    public static void reset() {
        wonGames = 0;
        lostGames = 0;
    }

    public static int getWonGames() {
        return wonGames;
    }

    public static int getLostGames() {
        return lostGames;
    }

    public static void setWonGames(int wonGames) {
        Scores.wonGames = wonGames;
    }

    public static void setLostGames(int lostGames) {
        Scores.lostGames = lostGames;
    }

}
