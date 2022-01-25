import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerInStream implements Runnable {

    private final ServerConnection connection;
    private final ObjectInputStream in;
    private boolean running = true;

    public ServerInStream(ObjectInputStream in, ServerConnection connection) {
        this.connection = connection;
        this.in = in;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("InStream running");
            try {
                connection.unpack(in.readObject());
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
