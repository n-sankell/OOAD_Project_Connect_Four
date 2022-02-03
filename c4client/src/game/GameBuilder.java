package game;

import game.network.ClientConnection;
import gui.GuiBoard;
import gui.GuiColors;

import java.awt.*;

public class GameBuilder {

    private ClientConnection connection;

    public GuiBoard onePlayerModeEasy(String namePlayer1, Color selectedColor) {
        Player player1 = new Player(1,selectedColor);
        AI ai = new AI(2,GuiColors.AI_PIECE_EASY,1);
        player1.setName(namePlayer1);
        Board board = new Board(player1, ai, GameMode.ONE_PLAYER);
        BoardLogic logic = new BoardLogic(board);
        board.setLogic(logic);
        return new GuiBoard(board);
    }

    public GuiBoard onePlayerModeNormal(String namePlayer1, Color selectedColor) {
        Player player1 = new Player(1,selectedColor);
        AI ai = new AI(2,GuiColors.AI_PIECE_NORMAL,2);
        player1.setName(namePlayer1);
        Board board = new Board(player1, ai, GameMode.ONE_PLAYER);
        BoardLogic logic = new BoardLogic(board);
        board.setLogic(logic);
        return new GuiBoard(board);
    }

    public GuiBoard twoPlayerMode(String namePlayer1, String namePlayer2, Color selectedColorOne, Color selectedColorTwo) {
        Player player1 = new Player(1,selectedColorOne);
        Player player2 = new Player(2,selectedColorTwo);
        player1.setName(namePlayer1);
        player2.setName(namePlayer2);
        Board board = new Board(player1, player2, GameMode.TWO_PLAYERS);
        BoardLogic logic = new BoardLogic(board);
        board.setLogic(logic);
        return new GuiBoard(board);
    }

    public void networkMode(String namePlayer1, Color selectedColor) {
        connection = new ClientConnection(namePlayer1,selectedColor,5555);
        connection.startInStream();
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public GuiBoard getNetworkBoard() {
        return new GuiBoard(connection.getGameBoard());
    }

}
