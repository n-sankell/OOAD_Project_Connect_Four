package GAME.CLIENT;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInStream implements Runnable {

    private final ObjectInputStream in;
    private boolean running = true;
    private final PackageHandler handler;
    private ClientConnection client;

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
                    Object o = in.readObject();
                    System.out.println(o.toString());
                    client.unpack(o);
                    System.out.println("package received - SET UP");

                } else if (client.getState() == States.PLAYING_GAME) {
                    System.out.println("package received - PLAY");
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
