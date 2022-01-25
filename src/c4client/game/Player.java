package c4client.game;

import c4client.gui.GuiColors;

import java.awt.*;

public class Player {
    private String name;
    private int team;
    private int score = 0;
    private Color playerColor;

    public Player(int team, Color playerColor) {
        this.team = team;
        this.playerColor = playerColor;
        setStandardColors();
    }

    private void setStandardColors() {
        if (playerColor == null && team == 1) {
            this.playerColor = GuiColors.PIECE_YELLOW;
        } else if (playerColor == null && team == 2) {
            this.playerColor = GuiColors.PIECE_RED;
        }
    }

    public void setName(String name) {
        this.name = name;
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

    public void setTeam(int team) {
        this.team = team;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color color) {
        this.playerColor = color;
    }
}
