package SERVER.PACKAGES;

import GAME.Player;

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
