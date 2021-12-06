import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame implements ActionListener {

    private ActionListener insert;
    private final int rows = 6;
    private final int columns = 7;
    private final JPanel basePanel = new JPanel();
    private final JPanel insertPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JButton[] insertButtons = new JButton[columns];
    private final JLabel[][] holes = new JLabel[rows][columns];
    private final ImageIcon emptyCircle = new ImageIcon(createCircle());

    public GUI() {
        super("FYRA-I-RAD");
        setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        addBasePanel();
        addInsertButtons();
        refreshBoard();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public void refreshBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                holes[i][j] = new JLabel();
                holes[i][j].setIcon(emptyCircle);
                holes[i][j].setBackground(Color.BLUE);
                holes[i][j].setHorizontalAlignment(0);
                holes[i][j].setVisible(true);
                holes[i][j].setOpaque(true);
                boardPanel.add(holes[i][j]);
            }
        }
    }

    public void addInsertButtons() {
        for (int i = 0; i < columns; i++) {
            insertButtons[i] = new JButton("INSERT");
            insertButtons[i].setBackground(Color.blue);
            insertButtons[i].setForeground(Color.white);
            insertButtons[i].setOpaque(true);
            insertButtons[i].setBorderPainted(true);
            insertButtons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
            insertButtons[i].addActionListener(insert);
            insertPanel.add(insertButtons[i]);
        }
    }

    public void addBasePanel() {
        basePanel.setLayout(new BorderLayout());
        basePanel.setVisible(true);
        add(basePanel);
        boardPanel.setLayout(new GridLayout(rows, columns));
        basePanel.add(boardPanel, BorderLayout.CENTER);
        insertPanel.setLayout(new GridLayout(1, columns));
        basePanel.add(insertPanel, BorderLayout.NORTH);
    }

    public BufferedImage createCircle() {
        BufferedImage bufferedImage = new BufferedImage(110, 110, BufferedImage.TYPE_INT_ARGB);
        Color transparent = new Color(0x00FFFFFF, true);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(transparent);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawOval(5, 5, 100, 100);
        g.setColor(Color.WHITE);
        g.fillOval(5, 5, 100, 100);
        return bufferedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
