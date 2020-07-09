package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class CombosList {
    private final List<Combo> combos = new ArrayList<>();

    public List<Combo> getCombos() {
        return combos;
    }

    public void addCombo(Tile tile1, Tile tile2, Tile tile3) {
        combos.add(new Combo(tile1, tile2, tile3));
    }
}
