package packages;

import java.io.Serializable;

public class NewRoundPackage implements Serializable {

    private final int roundCount;

    public NewRoundPackage(int roundCount) {
        this.roundCount = roundCount;
    }

    public int getRoundCount() {
        return roundCount;
    }
}
