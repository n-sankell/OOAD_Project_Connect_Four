package client.game.network.packages;

import java.io.Serializable;

public class MovePackage implements Serializable {

    private final int chosenColumn;

    public MovePackage(int chosenColumn) {
        this.chosenColumn = chosenColumn;
    }

    public Object getMove() {
        return chosenColumn;
    }
}
