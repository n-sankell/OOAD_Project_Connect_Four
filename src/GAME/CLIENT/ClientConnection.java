package GAME.CLIENT;

import GAME.Board;
import GAME.CLIENT.PACKAGES.OpponentNamePackage;
import GAME.CLIENT.PACKAGES.PlayerNamePackage;
import GAME.Player;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection {

    private final int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final PackageHandler handler = new PackageHandler();
    private Board board;
    private Boolean searching;
    private Player player;
    private Player opponent;

    public ClientConnection(String playerName, Color chosenColor, int port) {

        this.port = port;
        searching = true;
        setupSocket();
        setupStreams();
        startInStream();
        sendPackage(new PlayerNamePackage(playerName));
        sendPackage(new OpponentNamePackage(playerName));
    }

    private void setupSocket() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            socket = new Socket(ip, port);
        } catch (ConnectException e) {
            System.out.println("Server is not running");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStreams() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startInStream() {
        ClientInStream inStream = new ClientInStream(in, handler);
        Thread inStreamThread = new Thread(inStream);
        inStreamThread.start();
    }

    public void sendPackage(Object o) {
        try {
            out.writeObject(o);
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHandlerListener(PackageListener listener) {
        handler.setListener(listener);
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