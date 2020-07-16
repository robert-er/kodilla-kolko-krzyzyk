package com.kodilla.tictactoe;

public class Computer {

    private static GameState gameState = GameState.getGameState();
    private static Content content = gameState.getContent();
    private static Tile[][] board = content.getBoard().getTiles();
    private static int[][] prediction = new int[3][3];

    public static void playComputer() throws InterruptedException {
        System.out.println("Computer.playComputer");
        Tile.setComputerTurn(true);
        if (Tile.isPlayable()) {
            for (Tile[] tile : board) {
                for (Tile tile1 : tile) {
                    if (!tile1.isSelected()) {
                        System.out.println("tile value " + tile1.getValue());
                        tile1.playComputer();
                        break;
                    }
                }
            }

//            for (Tile[] tile2 : board) {
//                for (Tile tile3 : tile2) {
//                    System.out.println(" all tiles vlues: " + tile3.getValue());
//                }
//            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[j][i].getValue() == Game.type.X) {
                        prediction[j][i] = -1;
                    }
                    if (board[j][i].getValue() == Game.type.O) {
                        prediction[j][i] = 1;
                    }
                    System.out.println(" all tiles vlues: " + prediction[j][i]);
                }
            }
        }
    }


}
