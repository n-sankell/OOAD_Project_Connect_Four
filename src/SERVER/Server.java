package SERVER;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private static final List<ServerConnection> CONNECTION_LIST = new ArrayList<>();
    private boolean running = true;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (running) {
            new Thread(this).start();

        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("running");
                Socket socket = serverSocket.accept();
                acceptConnection(socket);
                System.out.println("running");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptConnection(Socket socket) {
        String uniqueID = UUID.randomUUID().toString();
        ServerConnection connection = new ServerConnection(socket, uniqueID);
        CONNECTION_LIST.add(connection);
        System.out.println(CONNECTION_LIST);
        lookForPartner(connection);
    }

    public synchronized static void lookForPartner(ServerConnection looker) {
        for (ServerConnection connection : CONNECTION_LIST) {
            if (connection.isLookingForOpponent() && !connection.getId().equals(looker.getId())) {
                connection.setLookingForOpponent(false);
                looker.setLookingForOpponent(false);
                new GameRoom(looker, connection);
            }
        }
    }

}
