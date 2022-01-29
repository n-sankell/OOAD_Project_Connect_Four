package server;

import game.Player;
import packages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

    private ObjectInputStream readerIn;
    private ObjectOutputStream writerOut;
    private ServerConnection opponent;
    private final PackageHandler handler = new PackageHandler();
    private boolean looking = true;
    private final Socket socket;
    private final String uniqueID;
    private Player player;

    public ServerConnection(Socket socket, String uniqueID) {
        this.socket = socket;
        this.uniqueID = uniqueID;
        setupStreams();
        startInStream();
        setHandler();
    }

    private void setupStreams() {
        try {
            writerOut = new ObjectOutputStream(socket.getOutputStream());
            readerIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startInStream() {
        ServerInStream inStream = new ServerInStream(readerIn, handler);
        Thread inStreamThread = new Thread(inStream);
        inStreamThread.start();
    }

    public void sendPackage(Object o) {
        try {
            writerOut.writeObject(o);
            writerOut.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isLookingForOpponent() {
        return looking;
    }

    public void setLookingForOpponent(boolean looking) {
        this.looking = looking;
    }

    public String getId() {
        return uniqueID;
    }

    public void setOpponent(ServerConnection opponent) {
        this.opponent = opponent;
    }

    public Player getPlayer() {
        return player;
    }

    private void setHandlerListener(PackageListener listener) {
        handler.setListener(listener);
    }

    public void setHandler() {
        setHandlerListener((event, o) -> {
            switch (event) {
                case 2 -> {
                    PlayerPackage playerPackage = (PlayerPackage) o;
                    player = playerPackage.getPlayer();
                    opponent.sendPackage(new PlayerPackage(player));
                }
                case 3 -> {
                    ClientMessage chatMessage = (ClientMessage) o;
                    opponent.sendPackage(chatMessage);
                }
                case 4 -> {
                    MovePackage move = (MovePackage) o;
                    opponent.sendPackage(move);
                }
                case 5 -> {
                    if (player.getTeam() == 1) {
                        sendPackage(new StartPackage());
                        System.out.println("sent start package to: "+player.getName());
                    }
                }
            }
        });
    }

}
