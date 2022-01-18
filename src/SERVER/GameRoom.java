package SERVER;

import SERVER.PACKAGES.OpponentNamePackage;
import SERVER.PACKAGES.PlayerNamePackage;
import SERVER.PACKAGES.TeamPackage;

public class GameRoom {

    private ServerConnection player1;
    private ServerConnection player2;

    public GameRoom(ServerConnection player1, ServerConnection player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setOpponent(player2);
        player2.setOpponent(player1);

        player1.sendPackage(new TeamPackage(1));
        player2.sendPackage(new TeamPackage(2));
        startGame();
    }

    private void startGame() {
        while (true) {
            System.out.println("Start Game");
            System.out.println("Player 1 name:" + player1.getName());
            System.out.println("Player 2 name:" + player2.getName());
        }
    }

}
