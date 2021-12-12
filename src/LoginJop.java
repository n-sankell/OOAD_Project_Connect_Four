import javax.swing.*;

public class LoginJop {

    LoginJop() {}

    public String optionPane() {
        return JOptionPane.showInputDialog(null,
                 createPanel(),
                 "CREATE PLAYERS",
                 JOptionPane.PLAIN_MESSAGE);
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon("login.png"));
        panel.add(label);
        panel.setSize(600, 300);
        panel.setVisible(true);

        return panel;
    }
}