import javax.swing.*;
import java.awt.*;

public class GuiFrame extends JFrame {

    public GuiFrame() {
        super("FYRA-I-RAD");
        setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        GuiBoard board = new GuiBoard();
        add(board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

}
