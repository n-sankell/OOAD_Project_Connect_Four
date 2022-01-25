package game;

import packages.PlayerPackage;
import server.ServerConnection;

import packages.TeamPackage;

public class GameRoom {

    private ServerConnection player1;
    private ServerConnection player2;
    private boolean running = true;

    public GameRoom(ServerConnection player1, ServerConnection player2) {
        System.out.println("Game Room");
        this.player1 = player1;
        this.player2 = player2;
        player1.setOpponent(player2);
        player2.setOpponent(player1);

        player1.sendPackage(new TeamPackage(1));
        System.out.println("player 1 - 1");
        player2.sendPackage(new TeamPackage(2));
        System.out.println("player 2 - 2");
        startGame();
    }

    private void startGame() {
        System.out.println("Start Game");
        while (true) {
          if (player1.getPlayer() != null && player2.getPlayer() != null) {
              break;
          }
        }
        System.out.println("Player 1 name:" + player1.getPlayer().getName());
        System.out.println("Player 2 name:" + player2.getPlayer().getName());
        player1.sendPackage(new PlayerPackage(player2.getPlayer()));
        player2.sendPackage(new PlayerPackage(player1.getPlayer()));
    }

}
