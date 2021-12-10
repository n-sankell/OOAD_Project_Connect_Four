import javax.swing.*;

public class Player {
    private String name;
    private final int team;
    private int score = 0;

    public Player(int team) {
        this.team = team;
    }

    public void setName() {
        name = JOptionPane.showInputDialog(null, "ENTER YOUR NAME, PLAYER " + team);
        while (name == null || name.trim().equals("")) {
            name = JOptionPane.showInputDialog(null, "ENTER VALID NAME, PLAYER " + team);
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
}
