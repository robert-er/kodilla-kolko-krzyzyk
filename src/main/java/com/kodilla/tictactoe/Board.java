package com.kodilla.tictactoe;

public class Board {

    Tile[][] board = new Tile[3][3];

    public Tile[][] getTiles() {
        return board;
    }

    public void createBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(15 + (200 * j));
                tile.setTranslateY(15 + (200 * i));
                board[j][i] = tile;
            }
        }
    }
}

