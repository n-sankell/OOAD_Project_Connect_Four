package gui;

import game.Board;
import game.GameMode;
import game.PlayerTurn;

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
    private final ImageIcon insertButtonImage = new ImageIcon("resources/insert_button.png");

    public GuiBoard(Board board) {
        this.board = board;
        this.rows = board.getRows();
        this.columns = board.getColumns();
        setUpInsertButtons();
        setUpBasePanel();
        setUpdateHandler();
        setUpStatusPanel();
    }

    public void start() {
        board.newGame();
        setStatusPanelTexts();
        addInsertButtons();
        addBasePanel();
        updateBoard();
        addStatusPanel();
        repaint();
        revalidate();
    }

    public void setUpdateHandler() {
        board.setGuiUpdateListener(() -> {
            updateBoard();
            updateStatusPanel();
        });
    }

    private void setUpBasePanel() {
        setLayout(new BorderLayout());
        setVisible(true);
        insertPanel.setLayout(new GridLayout(1, columns));
        boardPanel.setLayout(new GridLayout(rows, columns));
    }

    private void addBasePanel() {
        add(insertPanel, BorderLayout.NORTH);
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

    private void setUpInsertButtons() {
        insertButtons = new JButton[columns];
        for (int i = 0; i < columns; i++) {
            insertButtons[i] = new JButton();
            insertButtons[i].setIcon(insertButtonImage);
            insertButtons[i].setBackground(GuiColors.TEXT);
            insertButtons[i].setOpaque(true);
            insertButtons[i].setBorderPainted(true);
            insertButtons[i].setBorder(BorderFactory.createLineBorder(GuiColors.TEXT, 0, false));
            insertButtons[i].addActionListener(this);
        }
    }

    private void addInsertButtons() {
        for (JButton button : insertButtons) {
            insertPanel.add(button);
        }
    }

    private void addStatusPanel() {
        statusPanel.add(scorePlayerOne);
        statusPanel.add(status);
        statusPanel.add(scorePlayerTwo);
    }

    private void setUpStatusPanel() {
        statusPanel.setBackground(GuiColors.BOARD);
        GridLayout grid = new GridLayout(1,3);
        grid.setHgap(50);
        statusPanel.setLayout(grid);
        scorePlayerOne = new JLabel();
        scorePlayerOne.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerOne.setHorizontalAlignment(SwingConstants.RIGHT);
        scorePlayerOne.setForeground(board.getPlayer1().getPlayerColor());
        status = new JTextPane();
        status.setFont(new Font("Druk Wide",Font.BOLD,20));
        StyledDocument documentStyle = status.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        status.setForeground(board.getCurrentPlayer().getPlayerColor().brighter());
        status.setBackground(Color.BLACK);
        scorePlayerTwo = new JLabel();
        scorePlayerTwo.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerTwo.setHorizontalAlignment(SwingConstants.LEFT);
        scorePlayerTwo.setForeground(board.getPlayer2().getPlayerColor());
    }

    public void setStatusPanelTexts() {
        scorePlayerOne.setText("SCORE "+board.getPlayer1().getName()+": "+board.getPlayer1().getScore());
        status.setText(getStatusText());
        status.setForeground(board.getCurrentPlayer().getPlayerColor().brighter());
        scorePlayerTwo.setText("SCORE "+board.getPlayer2().getName()+": "+board.getPlayer2().getScore());
    }

    private String getStatusText() {
        if (board.getGameMode() == GameMode.NETWORK && board.getPlayerTurn() == PlayerTurn.NOT_YOUR_TURN && board.isEmpty()) {
            return "WAIT FOR " +board.getCurrentPlayer().getName()+" TO BEGIN!";
        } else if (board.isEmpty()) {
            return board.getCurrentPlayer().getName()+" BEGINS!";
        } else if (board.getGameMode() == GameMode.ONE_PLAYER) {
            return "";
        } else if (board.getGameMode() == GameMode.NETWORK && board.getPlayerTurn() == PlayerTurn.NOT_YOUR_TURN) {
            return "WAIT FOR "+board.getCurrentPlayer().getName()+"'S MOVE!";
        } else {
            return "YOUR TURN "+board.getCurrentPlayer().getName()+"!";
        }
    }

    private void updateStatusPanel() {
        statusPanel.remove(scorePlayerOne);
        statusPanel.remove(status);
        statusPanel.remove(scorePlayerTwo);
        setStatusPanelTexts();
        addStatusPanel();
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
