package client.gui;

import client.game.Board;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiBoard extends JPanel implements ActionListener {
    private final Board board;
    private final int rows;
    private final int columns;
    private int chosenColumn;
    private JButton clicked = new JButton();
    private final JPanel insertPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel statusPanel = new JPanel();
    private JLabel scorePlayerOne;
    private JTextPane status;
    private JLabel scorePlayerTwo;
    private JButton[] insertButtons;
    private final ImageIcon insertButtonImage = new ImageIcon("ASSETS/insertButton.png");

    public GuiBoard(Board board) {
        this.board = board;
        rows = board.getRows();
        columns = board.getColumns();
        addBasePanel();
        board.newGame();
        setStatusPanel();
        updateBoard();
        repaint();
        revalidate();
    }

    private void addBasePanel() {
        setLayout(new BorderLayout());
        setVisible(true);
        insertPanel.setLayout(new GridLayout(1, columns));
        addInsertButtons();
        add(insertPanel, BorderLayout.NORTH);
        boardPanel.setLayout(new GridLayout(rows, columns));
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }

    private void updateBoard() {
        boardPanel.removeAll();
        addCircles();
        repaint();
        revalidate();
    }

    private void addCircles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boardPanel.add(board.getCircles()[i][j]);
                repaint();
                revalidate();
            }
        }
    }

    private void addInsertButtons() {
        insertButtons = new JButton[columns];
        for (int i = 0; i < columns; i++) {
            insertButtons[i] = new JButton();
            insertButtons[i].setIcon(insertButtonImage);
            insertButtons[i].setBackground(GuiColors.TEXT);
            insertButtons[i].setOpaque(true);
            insertButtons[i].setBorderPainted(true);
            insertButtons[i].setBorder(BorderFactory.createLineBorder(GuiColors.TEXT, 0, false));
            insertButtons[i].addActionListener(this);
            insertPanel.add(insertButtons[i]);
        }
    }

    private void setStatusPanel() {
        statusPanel.setBackground(GuiColors.BOARD);
        GridLayout grid = new GridLayout(1,3);
        grid.setHgap(50);
        statusPanel.setLayout(grid);
        scorePlayerOne = new JLabel("SCORE "+board.getPlayer1().getName()+": "+board.getPlayer1().getScore());
        scorePlayerOne.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerOne.setHorizontalAlignment(SwingConstants.RIGHT);
        scorePlayerOne.setForeground(board.getPlayer1().getPlayerColor());
        status = new JTextPane();
        status.setText(getStatusbarText());
        status.setFont(new Font("Druk Wide",Font.BOLD,20));
        StyledDocument documentStyle = status.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        status.setForeground(board.getCurrentPlayer().getPlayerColor().brighter());
        status.setBackground(Color.BLACK);
        scorePlayerTwo = new JLabel("SCORE "+board.getPlayer2().getName()+": "+board.getPlayer2().getScore());
        scorePlayerTwo.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerTwo.setHorizontalAlignment(SwingConstants.LEFT);
        scorePlayerTwo.setForeground(board.getPlayer2().getPlayerColor());
        statusPanel.add(scorePlayerOne);
        statusPanel.add(status);
        statusPanel.add(scorePlayerTwo);
    }

    private String getStatusbarText() {
        if (board.getGameMode() == 1 && board.isEmpty()) {
            return "BEGIN "+board.getCurrentPlayer().getName()+"!";
        } else if (board.getGameMode() == 1) {
            return "";
        } else if (board.isEmpty()) {
            return "BEGIN "+board.getCurrentPlayer().getName()+"!";
        } else {
            return "YOUR TURN "+board.getCurrentPlayer().getName()+"!";
        }
    }

    private void updateStatusPanel() {
        statusPanel.remove(scorePlayerOne);
        statusPanel.remove(status);
        statusPanel.remove(scorePlayerTwo);
        setStatusPanel();
        repaint();
        revalidate();
    }

    private int findInsert() {
        for (int i = 0; i < columns; i++) {
            if (clicked == insertButtons[i]) {
                chosenColumn = i;
            }
        }
        return chosenColumn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clicked = (JButton) e.getSource();
        board.makeMove(findInsert());
        updateStatusPanel();
        updateBoard();
        repaint();
        revalidate();
    }

}
