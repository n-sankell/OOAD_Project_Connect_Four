package game.network.packages;

import game.Player;

import java.io.Serializable;

public class PlayerPackage implements Serializable {

    private final Player player;

    public PlayerPackage(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
