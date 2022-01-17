package SERVER;

import SERVER.PACKAGES.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

    private ObjectInputStream readerIn;
    private ObjectOutputStream writerOut;
    private ServerConnection opponent;
    private ServerInStream inStream;
    private boolean looking = true;
    private Socket socket;
    private final String uniqueID;
    private String playerName;

    public ServerConnection(Socket socket, String uniqueID) {
        this.socket = socket;
        this.uniqueID = uniqueID;
        setupStreams();
        getPlayerName();
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
        inStream = new ServerInStream(readerIn,this);
        Thread inStreamThread = new Thread(inStream);
        inStreamThread.start();
    }

    private void getPlayerName() {
        try {
            unpack(readerIn.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public ServerConnection getOpponent() {
        return opponent;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getName() {
        return playerName;
    }

    public void unpack(Object o) {
        if (o instanceof PlayerNamePackage namePackage) {
            setPlayerName(namePackage.getName());
            getOpponent().sendPackage(new OpponentNamePackage(getName()));
        } else if (o instanceof OpponentNamePackage opponentName) {
            getOpponent().setPlayerName(opponentName.getName());
        } else if (o instanceof ClientMessage chatMessage) {
            getOpponent().sendPackage(chatMessage);
        } else if (o instanceof MovePackage move) {
            getOpponent().sendPackage(move);
        } else if (o instanceof ScorePackage score) {
            getOpponent().sendPackage(score);
        }

    }

}
