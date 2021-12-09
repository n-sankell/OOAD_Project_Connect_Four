import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiFrame extends JFrame implements ActionListener {

    private JMenuItem i1;
    private JMenuItem i2;
    private GuiBoard board;
    private final GameBuilder game = new GameBuilder();

    public GuiFrame() {
        super("CONNECT FOUR");
        setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == i1) {
            if (board != null) {
                remove(board);
            }
            add(board = game.onePlayerMode());
            repaint();
            revalidate();
        }
        if (e.getSource() == i2) {
            if (board != null) {
                remove(board);
            }
            add(board = game.twoPlayerMode());
            repaint();
            revalidate();
        }
    }
}
