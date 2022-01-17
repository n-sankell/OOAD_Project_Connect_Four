package SERVER;

public class StartServer {

    public static void main(String[] args) {
        Server server = new Server(5555);
        server.start();
    }

}
