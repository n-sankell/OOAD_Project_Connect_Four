import javax.swing.*;
import java.awt.*;

public class GuiFrame extends JFrame {

    public GuiFrame(Player player1, Player player2) {
        super("FYRA-I-RAD");
        setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        GuiBoard board = new GuiBoard(player1, player2);
        add(board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

}
