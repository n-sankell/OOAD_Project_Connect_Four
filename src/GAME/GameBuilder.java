package GAME;

import GUI.GuiBoard;
import GUI.GuiColors;

public class GameBuilder {

    public GuiBoard onePlayerModeEasy(String namePlayer1) {
        Player player1 = new Player(1, GuiColors.PIECE_YELLOW);
        AI ai = new AI(2,GuiColors.PIECE_BLUE,1);
        player1.setName(namePlayer1);
        Board board = new Board(player1,ai,1,ai.getDifficulty());
        return new GuiBoard(board);
    }

    public GuiBoard onePlayerModeNormal(String namePlayer1) {
        Player player1 = new Player(1,GuiColors.PIECE_YELLOW);
        AI ai = new AI(2,GuiColors.PIECE_RED,2);
        player1.setName(namePlayer1);
        Board board = new Board(player1,ai,1,ai.getDifficulty());
        return new GuiBoard(board);
    }

    public GuiBoard twoPlayerMode(String namePlayer1, String namePlayer2) {
        Player player1 = new Player(1,GuiColors.PIECE_YELLOW);
        Player player2 = new Player(2,GuiColors.PIECE_RED);
        player1.setName(namePlayer1);
        player2.setName(namePlayer2);
        Board board = new Board(player1,player2,2,0);
        return new GuiBoard(board);
    }

}
