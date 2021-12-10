import java.awt.*;

import static javax.swing.UIManager.put;

public class Main {

    public static void main(String[] args) {
        put("OptionPane.background", Color.white);
        put("Panel.background", Color.white);
        put("OptionPane.messageFont", new Font("Avenir Next", Font.BOLD, 14));
        put("OptionPane.buttonFont", new Font("Avenir Next", Font.PLAIN, 12));
        new GuiFrame();
    }
}