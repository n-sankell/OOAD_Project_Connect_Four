package GAME;

import GAME.CLIENT.ClientConnection;
import GUI.GuiBoard;
import GUI.GuiColors;

import java.awt.*;

public class GameBuilder {

    ClientConnection connection;

    public GuiBoard onePlayerModeEasy(String namePlayer1, Color selectedColor) {
        Player player1 = new Player(1,selectedColor);
        AI ai = new AI(2,GuiColors.AI_PIECE_EASY,1);
        player1.setName(namePlayer1);
        Board board = new Board(player1,ai,1,ai.getDifficulty());
        return new GuiBoard(board);
    }

    public GuiBoard onePlayerModeNormal(String namePlayer1, Color selectedColor) {
        Player player1 = new Player(1,selectedColor);
        AI ai = new AI(2,GuiColors.AI_PIECE_NORMAL,2);
        player1.setName(namePlayer1);
        Board board = new Board(player1,ai,1,ai.getDifficulty());
        return new GuiBoard(board);
    }

    public GuiBoard twoPlayerMode(String namePlayer1, String namePlayer2, Color selectedColorOne, Color selectedColorTwo) {
        Player player1 = new Player(1,selectedColorOne);
        Player player2 = new Player(2,selectedColorTwo);
        compareColors(player1,player2);
        player1.setName(namePlayer1);
        player2.setName(namePlayer2);
        Board board = new Board(player1,player2,2,0);
        return new GuiBoard(board);
    }

    public void networkMode(String namePlayer1, Color selectedColor) {
        connection = new ClientConnection(namePlayer1,selectedColor,5555);
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public GuiBoard getNetworkBoard() {
        return new GuiBoard(connection.getGameBoard());
    }

    private void compareColors(Player player1, Player player2) {
        if (player1.getPlayerColor() == player2.getPlayerColor()) {
            Color darkerPlayerTwo = player2.getPlayerColor().darker();
            player2.setPlayerColor(darkerPlayerTwo);
        }
    }

}
