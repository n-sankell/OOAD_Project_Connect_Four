import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame implements ActionListener {

    private ActionListener insert;

    public BufferedImage createCircle() {

        BufferedImage bufferedImage = new BufferedImage(110, 110, BufferedImage.TYPE_INT_ARGB);
        Color transparent = new Color(0x00FFFFFF, true);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(transparent);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawOval(5, 5, 100, 100);
        g.setColor(Color.WHITE);
        g.fillOval(5, 5, 100, 100);
        return bufferedImage;
    }

    ImageIcon emptyCircle = new ImageIcon(createCircle());

    GUI() {

        JFrame frame = new JFrame("FYRA-I-RAD");
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setVisible(true);
        frame.add(panel);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(7, 7));

        panel.add(boardPanel, BorderLayout.CENTER);
        JPanel insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(1, 7));
        //insertPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, false));
        panel.add(insertPanel, BorderLayout.NORTH);

        List<JLabel> holes = new ArrayList<>(42);

        for (int i = 0; i < 42; i++) {

            JLabel hole = new JLabel();
            hole.setIcon(emptyCircle);
            hole.setBackground(Color.BLUE);
            hole.setHorizontalAlignment(0);
            hole.setVisible(true);
            hole.setOpaque(true);
            holes.add(hole);
            boardPanel.add(hole);
        }
        for (int i = 0; i < 6; i++) {

            JButton insertButton = new JButton("INSERT");
            insertButton.setBackground(Color.blue);
            insertButton.setForeground(Color.white);
            insertButton.setOpaque(true);
            insertButton.setBorderPainted(true);
            insertButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
            insertButton.addActionListener(insert);
            insertPanel.add(insertButton);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
