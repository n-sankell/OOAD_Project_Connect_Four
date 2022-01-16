package GAME;

import java.awt.*;

public class ClientConnection {

    private Board board;
    private Boolean searching;
    private Player player;

    public ClientConnection(String playerName, Color chosenColor) {
        searching = true;
        searchingForOpponent();
    }

    private void searchingForOpponent() {
        while (searching) {

        }
    }

    public Board getGameBoard() {
        return board;
    }

    public Boolean isSearching() {
        return searching;
    }

}
