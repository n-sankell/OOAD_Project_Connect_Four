package game;

import packages.*;
import server.ServerConnection;

public class GameRoom {

    private final ServerConnection player1;
    private final ServerConnection player2;
    private final PackageHandler handler = new PackageHandler();
    private boolean running = true;

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
        System.out.println("Start Game");
        while (true) {
          if (player1.getPlayer() != null && player2.getPlayer() != null) {
              break;
          }
        }
        System.out.println("Player 1 name:" + player1.getPlayer().getName()+" team: "+player1.getPlayer().getTeam());
        System.out.println("Player 2 name:" + player2.getPlayer().getName()+" team: "+player1.getPlayer().getTeam());
        player1.sendPackage(new PlayerPackage(player2.getPlayer()));
        player2.sendPackage(new PlayerPackage(player1.getPlayer()));
    }

}
