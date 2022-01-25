package c4client.game.network.packages;

import java.io.Serializable;

public class OpponentNamePackage implements Serializable {

    private final String name;

    public String getName() {
        return name;
    }

    public OpponentNamePackage(String name) {
        this.name = name;
    }
}