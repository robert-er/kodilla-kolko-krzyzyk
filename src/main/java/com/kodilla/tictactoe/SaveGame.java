package com.kodilla.tictactoe;

import java.io.*;

public class SaveGame {
    private File savedGame = new File("save.txt");

    private Save save = new Save();
    private Save load = new Save();

    //    Tile.setDefaultValues();
   //         CombosList.setDefaultValues();
     //       Minimax.setDefaultValues();
       //     Computer.setDefaultValues();

    public void save() {
            save.setScoresWonGames(Scores.getWonGames());
            save.setScoresLostGames(Scores.getLostGames());

        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(savedGame));

            oos.writeObject(save);

            System.out.println("Savegame - scores");
            oos.close();
            System.out.println("SaveGame - close");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedGame));
            Object readSave = ois.readObject();
            if(readSave instanceof Save) {
              load = (Save) readSave;
                System.out.println("Loadgame - scores");
            }
            ois.close();
            System.out.println("Loadgame - close");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scores.setWonGames(load.getScoresWonGames());
        Scores.setLostGames(load.getScoresLostGames());
    }
}
