package game.network;

import game.Board;
import game.BoardLogic;
import game.GameMode;
import packages.*;
import game.Player;

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
    private NetworkBoardListener networkBoardListener;
    private final String playerName;
    private final Color chosenColor;

    private Board board;
    private Player player;
    private Player opponent;

    public ClientConnection(String playerName, Color chosenColor, int port) {
        this.port = port;
        this.playerName = playerName;
        this.chosenColor = chosenColor;
        setupSocket();
        setupStreams();
        setHandler();
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

    public void startInStream() {
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

    private void createPlayer(int teamNumber) {
        player = new Player(teamNumber,chosenColor);
        player.setTeam(teamNumber);
        player.setName(playerName);
    }

    public void setHandler() {
        setHandlerListener((event, o) -> {
            switch (event) {
                case 1 -> {
                    TeamPackage teamPackage = (TeamPackage) o;
                    createPlayer(teamPackage.getTeam());
                    sendPackage(new PlayerPackage(player));
                }
                case 2 -> {
                    PlayerPackage opponentPackage = (PlayerPackage) o;
                    opponent = opponentPackage.getPlayer();
                    createBoard();
                }
            }
        });
    }

    private void createBoard() {
        if (player.getTeam() == 1) {
            board = new Board(player, opponent, GameMode.NETWORK);
        } else {
            board = new Board(opponent, player, GameMode.NETWORK);
        }
        BoardLogic logic = new BoardLogic(board);
        board.setLogic(logic);
        board.setConnection(this);
        board.setGameHandler();
        sendPackage(new StartPackage());
        networkBoardListener.eventOccurred();
    }

    public Board getGameBoard() {
        return board;
    }

    public void setHandlerListener(PackageListener listener) {
        handler.setSetUpListener(listener);
    }

    public void setGameEventListener(PackageListener listener) {
        handler.setGameEventListener(listener);
    }

    public void setNetworkBoardListener(NetworkBoardListener listener) {
        this.networkBoardListener = listener;
    }

}