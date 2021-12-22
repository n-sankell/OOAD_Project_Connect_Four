import GUI.GuiFrame;

import java.awt.*;

import static javax.swing.UIManager.put;

public class Main {

    public static void main(String[] args) {
        put("OptionPane.background", Color.WHITE);
        put("Panel.background", Color.WHITE);
        put("OptionPane.messageFont", new Font("Druk Wide", Font.BOLD, 12));
        put("OptionPane.buttonFont", new Font("Druk Wide", Font.PLAIN, 12));
        new GuiFrame();
    }
}
