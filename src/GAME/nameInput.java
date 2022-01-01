package GAME;

import javax.swing.*;

public class nameInput {

    public String name;

    private String optionPane() {
        return JOptionPane.showInputDialog(null,
                createPanel(),
                "CREATE PLAYER",
                JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon("ASSETS/login.png"));
        panel.add(label);
        panel.setSize(600, 300);
        panel.setVisible(true);
        return panel;
    }

    public String inputName() {
        while (true) {
            name = optionPane();
            if (name == null) {
                break;
            } else if (!name.trim().equals("")) {
                name = name.trim();
                break;
            }
        }
        return name;
    }
}
