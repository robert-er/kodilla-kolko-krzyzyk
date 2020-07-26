package com.kodilla.tictactoe;

import java.io.*;

public class SaveGame {
    private File savedGame = new File("src/main/resources/savegame.txt");

    private Save save = new Save();
    private static Save load = new Save();

    public void save() {
            save.setScoresWonGames(Scores.getWonGames());
            save.setScoresLostGames(Scores.getLostGames());
            save.setScoresDrawGames(Scores.getDrawGames());
            save.setBoard();

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
        Scores.setDrawGames(load.getScoresDrawGames());

        Game.Type[][] gameTypeLoad =  load.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("SaveGame.load: j=" + j + " i=" + i + " type=" + gameTypeLoad[j][i]);

            }
        }
        System.out.println("wonGames=" + load.getScoresWonGames() + " lostGames=" + load.getScoresLostGames()
        + " drawGames=" + load.getScoresDrawGames());
        System.out.println("Scores.drawGames= " + Scores.getDrawGames());
    }

    public static Save getLoad() {
        return load;
    }

}
