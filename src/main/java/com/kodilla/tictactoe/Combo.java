package com.kodilla.tictactoe;

public class Combo {
    private Tile[] tiles;

    public Combo(Tile... tiles) {
        this.tiles = tiles;
    }

    public boolean isComplete() {
        if (!(tiles[0].getValue() == Game.type.O) && !(tiles[0].getValue() == Game.type.X))
            return false;
        return tiles[0].getValue().equals(tiles[1].getValue())
                && tiles[0].getValue().equals(tiles[2].getValue());
    }

    public Tile getTile(int i) {
        return tiles[i];
    }
}