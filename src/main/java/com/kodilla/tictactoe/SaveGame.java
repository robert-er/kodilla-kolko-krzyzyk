package com.kodilla.tictactoe;

import java.io.*;
import java.util.HashMap;

public class SaveGame {
    private File savedGame = new File("save.txt");

    private GameState gameState;

 //    Tile.setDefaultValues();
   //         CombosList.setDefaultValues();
     //       Minimax.setDefaultValues();
       //     Computer.setDefaultValues();

    public void save() {
        gameState = GameState.getGameState();
        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(savedGame));

            oos.writeObject(gameState);
            System.out.println("Savegame - write gameState");
            oos.close();
            System.out.println("SaveGame - close");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedGame));
            Object readMap = ois.readObject();
            if(readMap instanceof HashMap) {
              //  map.putAll((HashMap) readMap);
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
