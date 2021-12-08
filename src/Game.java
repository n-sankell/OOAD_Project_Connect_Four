public class Game {
    private Player player1 = new Player(1);
    private Player player2 = new Player(2);
    private int scorePlayer1;
    private int scorePlayer2;

    public Game(){
        new GuiFrame(player1, player2);
    }
}
