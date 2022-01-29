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
    private boolean looking = true;
    private final Socket socket;
    private final String uniqueID;
    private Player player;

    public ServerConnection(Socket socket, String uniqueID) {
        this.socket = socket;
        this.uniqueID = uniqueID;
        setupStreams();
        startInStream();
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
        ServerInStream inStream = new ServerInStream(readerIn,this);
        Thread inStreamThread = new Thread(inStream);
        inStreamThread.start();
    }

    public void sendPackage(Object o) {
        try {
            writerOut.writeObject(o);
            writerOut.reset();
            System.out.println("package sent");
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

    public void unpack(Object o) {
        if (o instanceof PlayerPackage playerPackage) {
            this.player = playerPackage.getPlayer();
        } else if (o instanceof ClientMessage chatMessage) {
            opponent.sendPackage(chatMessage);
        } else if (o instanceof MovePackage move) {
            opponent.sendPackage(move);
        } else if (o instanceof StartPackage) {
            sendPackage(new StartPackage());
        }

    }

}
