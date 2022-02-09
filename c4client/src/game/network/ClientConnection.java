package game.network;

import game.BoardSize;
import game.GameController;
import game.GameLogic;
import game.enums.GameMode;
import game.listeners.NetworkBoardListener;
import packages.*;
import game.Player;

import javax.swing.*;
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
    private final BoardSize boardSize;
    private final PackageHandler handler = new PackageHandler();
    private NetworkBoardListener networkBoardListener;
    private final String playerName;
    private final Color chosenColor;

    private GameController gameController;
    private Player player;
    private Player opponent;

    public ClientConnection(BoardSize boardSize, String playerName, Color chosenColor, int port) {
        this.port = port;
        this.playerName = playerName;
        this.chosenColor = chosenColor;
        this.boardSize = boardSize;
    }

    public boolean checkNetwork() {
        if (setupSocket()) {
            if (setupStreams()) {
                setHandler();
                return true;
            }
        }
        return false;
    }

    private boolean setupSocket() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            socket = new Socket(ip, port);
            return true;
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null,"Server is not running, try again later. ");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean setupStreams() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return false;
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
            gameController = new GameController(boardSize, player, opponent, GameMode.NETWORK);
        } else {
            gameController = new GameController(boardSize, opponent, player, GameMode.NETWORK);
        }
        GameLogic logic = new GameLogic(gameController);
        gameController.setLogic(logic);
        gameController.setConnection(this);
        gameController.setGameHandler();
        sendPackage(new StartPackage());
        networkBoardListener.eventOccurred();
    }

    public GameController getGameBoard() {
        return gameController;
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