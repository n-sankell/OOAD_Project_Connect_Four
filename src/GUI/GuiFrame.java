package GUI;

import GAME.GameBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class GuiFrame extends JFrame implements ActionListener {

    private JMenuItem i1a;
    private JMenuItem i1b;
    private JMenuItem i2;
    private JMenuItem i3;
    private GuiBoard board;
    private JPanel waiting;
    private final GameBuilder game = new GameBuilder();
    private JPanel welcome;

    public GuiFrame() {
        super("CONNECT FOUR");
        setPreferredSize(new Dimension(1000, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocation(250, 75);
        addWelcomeScreen();
        addMenu();
        pack();
    }

    private void addMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("GAME MODE");
        JMenu submenu = new JMenu("ONE PLAYER");
        submenu.setFont(new Font("Druk Wide", Font.BOLD, 12));
        menu.setFont(new Font("Druk Wide", Font.BOLD, 12));
        i1a = new JMenuItem("EASY");
        i1b = new JMenuItem("NORMAL");
        i2 = new JMenuItem("TWO PLAYERS");
        i3 = new JMenuItem("NETWORK");

        for (JMenuItem item : Arrays.asList(i1a, i1b, i2, i3)) {
            item.setFont(new Font("Druk Wide", Font.BOLD, 12));
            item.addActionListener(this);
        }
        menu.add(submenu);
        submenu.add(i1a);
        submenu.add(i1b);
        menu.add(i2);
        menu.add(i3);
        mb.add(menu);
        setJMenuBar(mb);
    }

    private void addWelcomeScreen() {
        try {
            welcome = new ImageBackground("ASSETS/connectFour.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (welcome != null) {
            welcome.setSize(1000, 800);
            welcome.setVisible(true);
            welcome.setOpaque(false);
            welcome.setLayout(new BorderLayout());
            add(welcome);
        }
    }

    private void removeItems() {
        if (board != null) {
            remove(board);
        } else if (welcome != null) {
            remove(welcome);
        } else if (waiting != null) {
            remove(waiting);
        }
    }

    private void addWaitingScreen() {
        try {
            waiting = new ImageBackground("ASSETS/waiting.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (waiting != null) {
            waiting.setSize(1000, 800);
            waiting.setVisible(true);
            waiting.setOpaque(false);
            waiting.setLayout(new BorderLayout());
            add(waiting);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NameInput nameInput = new NameInput();
        if (e.getSource() == i1a) {
            if (nameInput.inputName("PLAYER 1") != null) {
                removeItems();
                add(board = game.onePlayerModeEasy(nameInput.name, nameInput.selectedColor));
                repaint();
                revalidate();
            }
        }
        if (e.getSource() == i1b) {
            if (nameInput.inputName("PLAYER 1") != null) {
                removeItems();
                add(board = game.onePlayerModeNormal(nameInput.name, nameInput.selectedColor));
                repaint();
                revalidate();
            }
        }
        if (e.getSource() == i2) {
            String namePlayer1 = nameInput.inputName("PLAYER 1");
            Color selectColorOne = nameInput.selectedColor;
            if (namePlayer1 != null) {
                nameInput.selectedColor = null;
                String namePlayer2 = nameInput.inputName("PLAYER 2");
                Color selectColorTwo = nameInput.selectedColor;
                if (namePlayer2 != null) {
                    removeItems();
                    add(board = game.twoPlayerMode(namePlayer1, namePlayer2, selectColorOne, selectColorTwo));
                    repaint();
                    revalidate();
                }
            }
        }
        if (e.getSource() == i3) {
            if (nameInput.inputName("PLAYER") != null) {
                removeItems();
                addWaitingScreen();
                game.networkMode(nameInput.name, nameInput.selectedColor);
                repaint();
                revalidate();
                if (!game.getConnection().isSearching()) {
                    removeItems();
                    add(game.getNetworkBoard());
                    repaint();
                    revalidate();
                }
            }
        }
    }
}
