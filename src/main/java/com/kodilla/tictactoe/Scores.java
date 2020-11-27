package com.kodilla.tictactoe;

public class Scores {

    public static int wonGames;
    private static int lostGames;
    private static int drawGames;

    public static void won() {
        wonGames++;
    }

    public static void lost() {
        lostGames++;
    }

    public static void draw() {
        drawGames++;
    }

    public static void reset() {
        wonGames = 0;
        lostGames = 0;
        drawGames = 0;
    }

    public static int getWonGames() {
        return wonGames;
    }

    public static int getLostGames() {
        return lostGames;
    }

    public static int getDrawGames() {
        return drawGames;
    }

    public static void setWonGames(int wonGames) {
        Scores.wonGames = wonGames;
    }

    public static void setLostGames(int lostGames) {
        Scores.lostGames = lostGames;
    }

    public static void setDrawGames(int drawGames) {
        Scores.drawGames = drawGames;
    }
}
