import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CustomJop {

    private static Dialog d;

    CustomJop(String message) throws IOException {
        JFrame f= new JFrame();
        d = new JDialog(f , true);
        d.setLayout( new BorderLayout());
        JButton b = new JButton (new ImageIcon("okbutton.png"));
        b.addActionListener (e -> CustomJop.d.setVisible(false));
        b.setBounds(520,600,200,80);
        JLabel label = new JLabel(new ImageIcon("scoreboard.png"));
        d.add (label,BorderLayout.CENTER);
        JTextArea score = new JTextArea();
        score.setBounds(500,300,380,200);
        score.setOpaque(false);
        score.setWrapStyleWord(true);
        score.setLineWrap(true);
        score.setText(message);
        score.setForeground(Color.BLACK);
        score.setFont(new Font("Druk Wide",Font.BOLD,20));
        label.add(b);
        label.add(score);
        d.setSize(1000,800);
        d.setVisible(true);
    }
}