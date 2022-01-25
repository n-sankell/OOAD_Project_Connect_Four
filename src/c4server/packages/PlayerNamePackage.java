package c4server.packages;

import java.io.Serializable;

public class PlayerNamePackage implements Serializable {

    private final String name;

    public String getName() {
        return name;
    }

    public PlayerNamePackage(String name) {
        this.name = name;
    }
}