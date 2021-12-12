import java.awt.*;

public class Player {
    private String name;
    private final int team;
    private int score = 0;
    private final LoginJop lj = new LoginJop();
    private final Color playerColor;

    public Player(int team, Color playerColor) {
        this.team = team;
        this.playerColor = playerColor;
    }

    public void setName() {
        while (name == null || name.trim().equals("")) {
        name = lj.optionPane();
        }
        name = name.trim();
    }

    public String getName() {
        return name.toUpperCase();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getTeam() {
        return team;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
}
