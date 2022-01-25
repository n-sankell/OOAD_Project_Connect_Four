package c4server.packages;

import c4server.clientclasses.Player;

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
