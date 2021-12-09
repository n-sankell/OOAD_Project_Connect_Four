import javax.swing.*;
import java.awt.*;

public class GuiWelcome extends JPanel {

    public GuiWelcome() {
        GridLayout grid = new GridLayout(1,1);
        setLayout(grid);
        grid.setVgap(500);
        setBackground(GuiColors.BOARD);
        JLabel welcome = new JLabel("Welcome! Choose game mode!");
        welcome.setBackground(GuiColors.BOARD);
        welcome.setForeground(GuiColors.TEXT);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        add(welcome);
    }

}
