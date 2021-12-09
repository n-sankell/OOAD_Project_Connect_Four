public class GameBuilder {

    public GuiBoard onePlayerMode() {
        Player player1 = new Player(1);
        AI ai = new AI(2);
        player1.setName();
        ai.setName();
        return new GuiBoard(player1, ai,1);
    }

    public GuiBoard twoPlayerMode() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        player1.setName();
        player2.setName();
        return new GuiBoard(player1, player2,2);
    }

}
