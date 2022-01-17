package GAME.CLIENT.PACKAGES;

import java.io.Serializable;

public class ScorePackage implements Serializable {

    private final int score;

    public ScorePackage(int score) {
        this.score = score;
    }

    public Object getScore() {
        return score;
    }
}