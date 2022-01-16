package SERVER;

public class GameRoom {

    private ServerConnection player1;
    private ServerConnection player2;

    public GameRoom(ServerConnection player1, ServerConnection player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setOpponent(player2);
        player2.setOpponent(player1);

        startGame();
    }

    private void startGame() {

    }

}
