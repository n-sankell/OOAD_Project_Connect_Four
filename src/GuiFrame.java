import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiFrame extends JFrame implements ActionListener {
    JMenu menu;
    JMenuItem i1, i2;
    public GuiFrame(Player player1, Player player2) {
        super("FYRA-I-RAD");
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("GAME MODE");
        i1=new JMenuItem("two player");
        i2=new JMenuItem("one player");
        i1.addActionListener(this);
        i2.addActionListener(this);
        menu.add(i1); menu.add(i2);
        mb.add(menu);
        menu.setBackground(Color.black);
        setJMenuBar(mb);
        setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        GuiBoard board = new GuiBoard(player1, player2);
        add(board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
