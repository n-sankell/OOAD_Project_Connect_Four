package client.game.network;

import client.game.Board;
import client.game.network.packages.*;
import client.game.GameBuilder;
import client.game.Player;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection {

    private States state = States.SET_UP;
    private final int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final PackageHandler handler = new PackageHandler();
    private final String playerName;
    private String opponentName;
    private int playerTeam;
    private int opponentTeam;
    private Color chosenColor;
    private Color opponentColor;

    private Board board;
    private Player player;
    private Player opponent;

    public ClientConnection(String playerName, Color chosenColor, int port) {

        this.port = port;
        this.playerName = playerName;
        this.chosenColor = chosenColor;
        setupSocket();
        setupStreams();
        startInStream();
    }

    private void setupSocket() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            socket = new Socket(ip, port);
        } catch (ConnectException e) {
            System.out.println("Server is not running");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStreams() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void startInStream() {
        ClientInStream inStream = new ClientInStream(in, handler,this);
        Thread inStreamThread = new Thread(inStream);
        inStreamThread.start();
    }

    public void sendPackage(Object o) {
        try {
            out.writeObject(o);
            System.out.println("object sent");
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPlayer(int teamNumber) {
        player = new Player(teamNumber,chosenColor);
        player.setName(playerName);
    }

    public void setHandlerListener(PackageListener listener) {
        handler.setListener(listener);
    }

    public void unpack(Object o) {
        if (o instanceof TeamPackage teamPackage) {
            System.out.println(teamPackage.getTeam());
            createPlayer(teamPackage.getTeam());
            sendPackage(new PlayerPackage(player));
        } else if (o instanceof PlayerPackage opponentPackage) {
            opponent = opponentPackage.getPlayer();
            createPlayersAndBoard();
        }
    }

    private void createPlayersAndBoard() {
        GameBuilder.compareColors(player,opponent);
        board = new Board(player, opponent,3,0);
        state = States.PLAYING_GAME;
    }

    public Board getGameBoard() {
        return board;
    }

    public States getState() {
        return state;
    }

}