import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiFrame extends JFrame implements ActionListener {

    private JMenuItem i1;
    private JMenuItem i2;
    private GuiBoard board;
    private final GameBuilder game = new GameBuilder();
    private JPanel welcome;

    public GuiFrame() {
        super("CONNECT FOUR");
        setPreferredSize(new Dimension(1000, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocation(250,75);
        addWelcomeScreen();
        addMenu();
        pack();
    }

    public void addMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("GAME MODE");
        i1 = new JMenuItem("ONE PLAYER");
        i2 = new JMenuItem("TWO PLAYERS");
        i1.addActionListener(this);
        i2.addActionListener(this);
        menu.add(i1);
        menu.add(i2);
        mb.add(menu);
        setJMenuBar(mb);
    }

    public void addWelcomeScreen() {
        try {
            welcome = new ImageBackground("connectFour2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (welcome != null) {
            welcome.setSize(1000, 800);
            welcome.setVisible(true);
            welcome.setOpaque(false);
            welcome.setLayout(new BorderLayout());
            add(welcome);
        }
    }

    public void removeItems() {
        if (board != null) {
            remove(board);
        } else if (welcome != null) {
            remove(welcome);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == i1) {
            removeItems();
            add(board = game.onePlayerMode());
            repaint();
            revalidate();
        }
        if (e.getSource() == i2) {
            removeItems();
            add(board = game.twoPlayerMode());
            repaint();
            revalidate();
        }
    }
}
