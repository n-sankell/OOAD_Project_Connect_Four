import javax.swing.*;

public class Player {
    private String name;
    private int team;
    private int score;

    public Player(int team) {
        this.team = team;
        setName();
    }

    private void setName() {
        name = JOptionPane.showInputDialog(null, "Enter your name player " + team);
        while (name == null || name.trim().equals("")) {
            name = JOptionPane.showInputDialog(null, "Enter valid name, player "+team);
        }
        name = name.trim();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTeam() {
        return team;
    }
}
