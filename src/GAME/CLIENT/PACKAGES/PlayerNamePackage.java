package GAME.CLIENT.PACKAGES;

import java.io.Serializable;

public class PlayerNamePackage implements Serializable {

    private final String name;

    public String getName() {
        return name;
    }

    public PlayerNamePackage(String name) {
        this.name = name;
        System.out.println(this.name);
    }
}