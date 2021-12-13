import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private final int team;
    private int score = 0;
    private final Color playerColor;

    public Player(int team, Color playerColor) {
        this.team = team;
        this.playerColor = playerColor;
    }

    public String optionPane() {
        return JOptionPane.showInputDialog(null,
                createPanel(),
                "CREATE PLAYERS",
                JOptionPane.PLAIN_MESSAGE);
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon("login.png"));
        panel.add(label);
        panel.setSize(600, 300);
        panel.setVisible(true);

        return panel;
    }

    public void setName() {
        while (name == null || name.trim().equals("")) {
        name = optionPane();
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
