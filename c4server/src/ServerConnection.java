import packages.*;

import java.awt.*;
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
    private final Socket socket;
    private final String uniqueID;
    private String playerName;
    private Color color;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
        if (o instanceof PlayerPackage playerPackage) {
            System.out.println(playerPackage.getPlayer().getName());
            setPlayerName(playerPackage.getPlayer().getName());
        } else if (o instanceof ColorPackage color) {
            setColor(color.getColor());
        } else if (o instanceof ClientMessage chatMessage) {
            getOpponent().sendPackage(chatMessage);
        } else if (o instanceof MovePackage move) {
            getOpponent().sendPackage(move);
        } else if (o instanceof ScorePackage score) {
            getOpponent().sendPackage(score);
        }

    }

}
