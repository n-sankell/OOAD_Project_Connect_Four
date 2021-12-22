package GUI;

import GAME.Piece;
import GAME.Player;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class GuiBoard extends JPanel implements ActionListener {

    private final int rows = 6;
    private final int columns = 7;
    private final int gameMode;
    private final int difficulty;
    private int roundCounter = 0;
    private int chosenColumn;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private String winMessage;
    private String drawMessage;
    private JButton clicked = new JButton();
    private final JPanel insertPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel statusPanel = new JPanel();
    private JLabel scorePlayerOne;
    private JTextPane status;
    private JLabel scorePlayerTwo;
    private final JButton[] insertButtons = new JButton[columns];
    private final Piece[][] circles = new Piece[rows][columns];
    private final ImageIcon insertButtonImage = new ImageIcon("ASSETS/insertButton.png");

    public GuiBoard(Player player1, Player player2, int gameMode, int difficulty) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        setMessages();
        addBasePanel();
        setStatusPanel();
        newGame();
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

    private void newGame() {
        boardPanel.removeAll();
        roundCounter++;
        addCircles();
        if (roundCounter % 2 != 0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        updateStatusPanel();
        repaint();
        revalidate();
        if (gameMode == 1 && currentPlayer == player2) {
            aiTurn();
        }
    }

    private void addCircles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                circles[i][j] = new Piece(player1,player2);
                boardPanel.add(circles[i][j]);
            }
        }
    }

    private void addInsertButtons() {
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
        scorePlayerOne = new JLabel("SCORE "+player1.getName()+": "+player1.getScore());
        scorePlayerOne.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerOne.setHorizontalAlignment(SwingConstants.RIGHT);
        scorePlayerOne.setForeground(player1.getPlayerColor());
        status = new JTextPane();
        if (gameMode == 1) {
            status.setText("");
        } else {
            status.setText("YOUR TURN "+currentPlayer.getName()+"!");
        }
        status.setFont(new Font("Druk Wide",Font.BOLD,20));
        StyledDocument documentStyle = status.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        status.setForeground(currentPlayer.getPlayerColor().brighter());
        status.setBackground(Color.BLACK);
        scorePlayerTwo = new JLabel("SCORE "+player2.getName()+": "+player2.getScore());
        scorePlayerTwo.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerTwo.setHorizontalAlignment(SwingConstants.LEFT);
        scorePlayerTwo.setForeground(player2.getPlayerColor());
        statusPanel.add(scorePlayerOne);
        statusPanel.add(status);
        statusPanel.add(scorePlayerTwo);
    }

    private void updateStatusPanel() {
        statusPanel.remove(scorePlayerOne);
        statusPanel.remove(status);
        statusPanel.remove(scorePlayerTwo);
        setStatusPanel();
        repaint();
        revalidate();
    }

    private boolean checkColumn(int columnsNr) {
        for (int i = rows - 1; i >= 0; i--) {
            if (circles[i][columnsNr].getTeam() == 0) {
                return true;
            }
        }
        return false;
    }

    private void putPiece(int columnsNr, int teamNr) {
        for (int i = rows - 1; i >= 0; i--) {
            if (circles[i][columnsNr].getTeam() == 0) {
                circles[i][columnsNr].changeTeam(teamNr);
                break;
            }
        }
    }

    private void findInsert() {
        for (int i = 0; i < columns; i++) {
            if (clicked == insertButtons[i]) {
                chosenColumn = i;
            }
        }
    }

    private boolean checkWin() {
        return checkWinVertical() || checkWinHorizontal() || checkWinDiagonalUp() || checkWinDiagonalDown();
    }

    private boolean checkWinHorizontal() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i + 1][j].getTeam() == currentPlayer.getTeam() &&
                        circles[i + 2][j].getTeam() == currentPlayer.getTeam() && circles[i + 3][j].getTeam() == currentPlayer.getTeam()) {
                    setWinningColors(i, j, i + 1, j, i + 2, j, i + 3, j);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWinVertical() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i][j + 1].getTeam() == currentPlayer.getTeam() &&
                        circles[i][j + 2].getTeam() == currentPlayer.getTeam() && circles[i][j + 3].getTeam() == currentPlayer.getTeam()) {
                    setWinningColors(i, j, i, j + 1, i, j + 2, i, j + 3);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWinDiagonalUp() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i + 1][j + 1].getTeam() == currentPlayer.getTeam() &&
                        circles[i + 2][j + 2].getTeam() == currentPlayer.getTeam() && circles[i + 3][j + 3].getTeam() == currentPlayer.getTeam()) {
                    setWinningColors(i, j, i + 1, j + 1, i + 2, j + 2, i + 3, j + 3);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWinDiagonalDown() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i + 1][j - 1].getTeam() == currentPlayer.getTeam() &&
                        circles[i + 2][j - 2].getTeam() == currentPlayer.getTeam() && circles[i + 3][j - 3].getTeam() == currentPlayer.getTeam()) {
                    setWinningColors(i, j, i + 1, j - 1, i + 2, j - 2, i + 3, j - 3);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBoardFull() {
        int count = 0;
        for (int j = 0; j < columns; j++) {
            if (!checkColumn(j)) {
                count++;
                if (count == columns) {
                    return true;
                }
            }
        }
        return false;
    }

    private void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private void setWinningColors(int a, int b, int c, int d, int e, int f, int g, int h) {
        circles[a][b].winningPieces(currentPlayer.getTeam());
        circles[c][d].winningPieces(currentPlayer.getTeam());
        circles[e][f].winningPieces(currentPlayer.getTeam());
        circles[g][h].winningPieces(currentPlayer.getTeam());
    }

    private void setMessages() {
        winMessage = currentPlayer.getName() + " WINS THE ROUND!" + "\nSCORE FOR " + player1.getName() + ": " +
                player1.getScore() + "\n" + "SCORE FOR " + player2.getName() + ": " + player2.getScore();
        drawMessage = "IT'S A DRAW!" + "\nSCORE FOR " + player1.getName() + ": " + player1.getScore() + "\n" +
                "SCORE FOR " + player2.getName() + ": " + player2.getScore();
    }

    private void aiTurn() {
        currentPlayer = player2;
        while (true) {
            Random random = new Random();
            int aiRandomMove = random.nextInt(columns);
            int aiMove = GAME.AI.findMove(circles, rows, columns, currentPlayer.getTeam(), difficulty);
            if (checkColumn(aiMove)) {
                putPiece(aiMove, currentPlayer.getTeam());
                break;
            } else if (checkColumn(aiRandomMove)) {
                putPiece(aiRandomMove, currentPlayer.getTeam());
                break;
            }
        }
        if (checkWin()) {
            currentPlayer.setScore(1);
            setMessages();
            try {
                new CustomJop(winMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newGame();
        } else if (checkBoardFull()) {
            JOptionPane.showMessageDialog(null, drawMessage);
            newGame();
        } else {
            currentPlayer = player1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clicked = (JButton) e.getSource();
        findInsert();
        if (checkColumn(chosenColumn)) {
            putPiece(chosenColumn, currentPlayer.getTeam());
            repaint();
            revalidate();
            if (checkWin()) {
                currentPlayer.setScore(1);
                setMessages();
                try {
                    new CustomJop(winMessage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                newGame();
            } else if (checkBoardFull()) {
                try {
                    new CustomJop(drawMessage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                newGame();
            } else {
                if (gameMode == 1) {
                    aiTurn();
                } else if (gameMode == 2) {
                    changePlayer();
                }
            }
        }
        updateStatusPanel();
    }

}
