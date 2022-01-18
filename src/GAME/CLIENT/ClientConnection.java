package GAME.CLIENT;

import GAME.Board;
import GAME.CLIENT.PACKAGES.*;
import GAME.Player;

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
        sendPackage(new PlayerNamePackage(playerName));
        sendPackage(new ColorPackage(chosenColor));
        System.out.println(playerName);
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
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHandlerListener(PackageListener listener) {
        handler.setListener(listener);
    }

    public void unpack(Object o) {
        if (o instanceof OpponentNamePackage opponentNamePackage) {
            opponentName = opponentNamePackage.getName();
        } else if (o instanceof ColorPackage colorPackage) {
            opponentColor = colorPackage.getColor();
            createPlayersAndBoard();
        } else if (o instanceof TeamPackage teamPackage) {
            playerTeam = teamPackage.getTeam();
            if (playerTeam == 2) {
                opponentTeam = 1;
            } else if (playerTeam == 1 ) {
                opponentTeam = 2;
            }
        }
    }

    private void createPlayersAndBoard() {
        player = new Player(playerTeam,chosenColor);
        player.setName(playerName);
        opponent = new Player(opponentTeam,opponentColor);
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