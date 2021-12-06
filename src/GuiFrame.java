import javax.swing.*;
import java.awt.*;

public class GuiFrame extends JFrame {

    private final GuiBoard board = new GuiBoard();

    public GuiFrame() {
        super("FYRA-I-RAD");
        setPreferredSize(new Dimension(1000, 800));
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        add(board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

}
