package GUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;

public class CustomJop {

    private static Dialog d;

    public CustomJop(String message) throws IOException {
        JFrame f= new JFrame();
        d = new JDialog(f , true);
        d.setLayout( new BorderLayout());

        JButton b = new JButton (new ImageIcon("ASSETS/okButton.png"));
        b.addActionListener (e -> CustomJop.d.setVisible(false));
        b.setBounds(520,600,200,80);

        JLabel label = new JLabel(new ImageIcon("ASSETS/scoreBoard.png"));
        d.add (label,BorderLayout.CENTER);

        JTextPane score = new JTextPane();
        score.setBounds(386,300,450,600);
        score.setOpaque(false);
        score.setText(message);
        score.setForeground(GuiColors.BOARD);
        score.setFont(new Font("Druk Wide",Font.BOLD,30));
        StyledDocument documentStyle = score.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

        label.add(b);
        label.add(score);

        d.setSize(1000,800);
        d.setVisible(true);

    }
}