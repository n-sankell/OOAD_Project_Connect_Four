import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece extends JLabel {
    private int xPos;
    private int yPos;
    private int team; // 0: no team, 1: player 1, 2: player 2;
    private ImageIcon emptyCircle = new ImageIcon(createCircle(Color.WHITE));
    private ImageIcon teamOneCircle = new ImageIcon(createCircle(Color.YELLOW));
    private ImageIcon teamTwoCircle = new ImageIcon(createCircle(Color.RED));

    public Piece() {
        this.team = 0;
        this.setIcon(emptyCircle);
        this.setBackground(Color.BLUE);
        this.setHorizontalAlignment(0);
        this.setVisible(true);
        this.setOpaque(true);
    }

    public int getTeam() {
        return team;
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
