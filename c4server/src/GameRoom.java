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
        while (player1.getPlayer() == null || player2.getPlayer() == null) {
            try {
                System.out.println("waiting");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Player 1 name:" + player1.getPlayer().getName());
        System.out.println("Player 2 name:" + player2.getPlayer().getName());
    }

}
