package GAME.CLIENT;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInStream implements Runnable {

    private final ObjectInputStream in;
    private boolean running = true;
    private final PackageHandler handler;
    private ClientConnection client;
    private States state;

    public ClientInStream(ObjectInputStream in, PackageHandler handler, ClientConnection client) {
        this.in = in;
        this.handler = handler;
        this.client = client;
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (client.getState() == States.SET_UP) {
                    client.unpack(in.readObject());
                } else if (client.getState() == States.PLAYING_GAME) {
                    handler.unpack(in.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                close();
            }
        }
    }

    private void close() {
        try {
            in.close();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
