package gui;

import game.GameBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class GuiFrame extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenu subMenu;
    private JMenuItem i1a;
    private JMenuItem i1b;
    private JMenuItem i2;
    private JMenuItem i3;
    private JPanel welcome;
    private JPanel waiting;
    private GuiBoard guiBoard;
    private final GameBuilder gameBuilder = new GameBuilder();

    public GuiFrame() {
        super("CONNECT FOUR");
        setPreferredSize(new Dimension(1000, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocation(250, 75);
        setupWaitingScreen();
        setupWelcomeScreen();
        setUpMenu();
    }

    public void start() {
        addWelcome();
        addMenu();
        pack();
    }

    private void setUpMenu() {
        menuBar = new JMenuBar();
        mainMenu = new JMenu("GAME MODE");
        subMenu = new JMenu("ONE PLAYER");
        subMenu.setFont(new Font("Druk Wide", Font.BOLD, 12));
        mainMenu.setFont(new Font("Druk Wide", Font.BOLD, 12));
        i1a = new JMenuItem("EASY");
        i1b = new JMenuItem("NORMAL");
        i2 = new JMenuItem("TWO PLAYERS");
        i3 = new JMenuItem("NETWORK");
        for (JMenuItem item : Arrays.asList(i1a, i1b, i2, i3)) {
            item.setFont(new Font("Druk Wide", Font.BOLD, 12));
            item.addActionListener(this);
        }
    }

    private void addMenu() {
        mainMenu.add(subMenu);
        subMenu.add(i1a);
        subMenu.add(i1b);
        mainMenu.add(i2);
        mainMenu.add(i3);
        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
    }

    private void setupWelcomeScreen() {
        try {
            BufferedImage welcomeImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("images/connect_four.png")));
            JLabel welcomeBg = new JLabel(new ImageIcon(welcomeImage));
            welcome = new JPanel();
            welcome.setSize(1000, 800);
            welcome.setVisible(true);
            welcome.setOpaque(false);
            welcome.setLayout(new BorderLayout());
            welcome.add(welcomeBg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupWaitingScreen() {
        try {
            BufferedImage waitingImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("images/waiting.png")));
            JLabel waitingBg = new JLabel(new ImageIcon(waitingImage));
            waiting = new JPanel();
            waiting.setSize(1000, 800);
            waiting.setVisible(true);
            waiting.setOpaque(false);
            waiting.setLayout(new BorderLayout());
            waiting.add(waitingBg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeItems() {
        if (guiBoard != null) {
            remove(guiBoard);
        } if (welcome != null) {
            remove(welcome);
        } if (waiting != null) {
            remove(waiting);
        }
    }

    private void addWelcome() {
        add(welcome);
        repaint();
        revalidate();
    }

    public void addNetworkBoard() {
        removeItems();
        guiBoard = gameBuilder.getNetworkBoard();
        addGuiBoard();
    }

    private void addGuiBoard() {
        guiBoard.start();
        add(guiBoard);
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NameInput nameInput = new NameInput();
        if (e.getSource() == i1a) {
            if (nameInput.inputName("PLAYER 1") != null) {
                removeItems();
                guiBoard = gameBuilder.onePlayerModeEasy(nameInput.getName(), nameInput.getSelectedColor());
                addGuiBoard();
            }
        }
        if (e.getSource() == i1b) {
            if (nameInput.inputName("PLAYER 1") != null) {
                removeItems();
                guiBoard = gameBuilder.onePlayerModeNormal(nameInput.getName(), nameInput.getSelectedColor());
                addGuiBoard();
            }
        }
        if (e.getSource() == i2) {
            String namePlayer1 = nameInput.inputName("PLAYER 1");
            Color selectColorOne = nameInput.getSelectedColor();
            if (namePlayer1 != null) {
                nameInput.setSelectedColor(null);
                String namePlayer2 = nameInput.inputName("PLAYER 2");
                Color selectColorTwo = nameInput.getSelectedColor();
                if (namePlayer2 != null) {
                    removeItems();
                    guiBoard = gameBuilder.twoPlayerMode(namePlayer1, namePlayer2, selectColorOne, selectColorTwo);
                    addGuiBoard();
                }
            }
        }
        if (e.getSource() == i3) {
            if (nameInput.inputName("PLAYER") != null) {
                removeItems();
                add(waiting);
                repaint();
                revalidate();
                gameBuilder.networkMode(nameInput.getName(), nameInput.getSelectedColor());
                gameBuilder.getConnection().setNetworkBoardListener(this::addNetworkBoard);
            }
        }
    }
}
