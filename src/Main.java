import GUI.GuiStart;
import SERVER.Server;

public class Main {

    public static void main(String[] args) {
        new GuiStart();
        Server server = new Server(5555);
        server.start();
    }
}
