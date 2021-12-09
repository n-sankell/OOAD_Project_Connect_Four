public class GameBuilder {

    public GuiBoard onePlayerMode() {
        Player player1 = new Player(1);
        GameAI ai = new GameAI(2);
        player1.setName();
        ai.setName("AI");
        return new GuiBoard(player1, ai);
    }

    public GuiBoard twoPlayerMode() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        player1.setName();
        player2.setName();
        return new GuiBoard(player1, player2);
    }

}
