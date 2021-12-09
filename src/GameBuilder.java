public class GameBuilder {

    private Player player1;
    private Player player2;

    public GuiBoard onePlayerMode() {
        player1 = new Player(1);
        player2 = new Player(2);
        return new GuiBoard(player1, player2);
    }

    public GuiBoard twoPlayerMode() {
        player1 = new Player(1);
        player2 = new Player(2);
        return new GuiBoard(player1, player2);
    }

}
