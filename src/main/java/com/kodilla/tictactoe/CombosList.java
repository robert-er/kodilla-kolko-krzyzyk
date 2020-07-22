package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class CombosList {

    private static List<Combo> combos = new ArrayList<>();

    private final Tile[][] board;

    public CombosList(Tile[][] board) {
        this.board = board;
    }

    public List<Combo> getCombos() {
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }
        //vertical
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }
        //diagonal
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

        return combos;
    }

    public static void setDefaultValues() {
        combos = new ArrayList<>();
    }

}

