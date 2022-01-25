package c4client.game.network.packages;

import c4client.game.Player;

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
