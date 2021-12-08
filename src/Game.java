public class Game {

    public Game() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        new GuiFrame(player1, player2);
    }
}
