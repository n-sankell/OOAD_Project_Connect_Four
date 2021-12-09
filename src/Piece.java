import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece extends JLabel {
    private int team;
    private final ImageIcon teamOneCircle = new ImageIcon(createCircle(GuiColors.TEAM_ONE));
    private final ImageIcon teamTwoCircle = new ImageIcon(createCircle(GuiColors.TEAM_TWO));
    private final ImageIcon teamOneWin = new ImageIcon(createCircle(GuiColors.TEAM_ONE_WIN));
    private final ImageIcon teamTwoWin = new ImageIcon(createCircle(GuiColors.TEAM_TWO_WIN));

    public Piece() {
        this.team = 0;
        ImageIcon emptyCircle = new ImageIcon(createCircle(GuiColors.BG));
        this.setIcon(emptyCircle);
        this.setBackground(GuiColors.BOARD);
        this.setHorizontalAlignment(0);
        this.setVisible(true);
        this.setOpaque(true);
    }

    public int getTeam() {
        return team;
    }

    public void winningPieces(int team) {
        if (team == 1) {
            this.setIcon(teamOneWin);
            repaint();
            revalidate();
        }
        if (team == 2) {
            this.setIcon(teamTwoWin);
            repaint();
            revalidate();
        }
    }

    public void changeTeam(int teamTo) {
        if (team == 0) {
            this.team = teamTo;
            if (team == 1) {
                this.setIcon(teamOneCircle);
                repaint();
                revalidate();
            }
            if (team == 2 ) {
                this.setIcon(teamTwoCircle);
                repaint();
                revalidate();
            }
        }
    }

    public BufferedImage createCircle(Color color) {
        BufferedImage bufferedImage = new BufferedImage(110, 110, BufferedImage.TYPE_INT_ARGB);
        Color transparent = new Color(0x00FFFFFF, true);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(transparent);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawOval(5, 5, 100, 100);
        g.setColor(color);
        g.fillOval(5, 5, 100, 100);
        return bufferedImage;
    }

}
