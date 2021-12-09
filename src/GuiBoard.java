import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiBoard extends JPanel implements ActionListener {

    private final int rows = 6;
    private final int columns = 7;
    private int chosenColumn;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private String winMessage;
    private String drawMessage;
    private JButton clicked = new JButton();
    private final JPanel insertPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JButton[] insertButtons = new JButton[columns];
    private final Piece[][] circles = new Piece[rows][columns];

    public GuiBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        setMessages();
        addBasePanel();
        newGame();
    }

    public void addBasePanel() {
        setLayout(new BorderLayout());
        setVisible(true);
        boardPanel.setLayout(new GridLayout(rows, columns));
        add(boardPanel, BorderLayout.CENTER);
        insertPanel.setLayout(new GridLayout(1, columns));
        add(insertPanel, BorderLayout.NORTH);
        addInsertButtons();
    }

    public void newGame() {
        boardPanel.removeAll();
        addCircles();
        repaint();
        revalidate();
    }

    public void addCircles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                circles[i][j] = new Piece();
                boardPanel.add(circles[i][j]);
            }
        }
    }

    public void addInsertButtons() {
        for (int i = 0; i < columns; i++) {
            insertButtons[i] = new JButton("INSERT");
            insertButtons[i].setBackground(GuiColors.BUTTON);
            insertButtons[i].setForeground(GuiColors.TEXT);
            insertButtons[i].setOpaque(true);
            insertButtons[i].setBorderPainted(true);
            insertButtons[i].setBorder(BorderFactory.createLineBorder(GuiColors.BG, 1, false));
            insertButtons[i].addActionListener(this);
            insertPanel.add(insertButtons[i]);
        }
    }

    public boolean checkColumn(int columnsNr) {
        for (int i = rows-1; i >= 0; i--) {
            if (circles[i][columnsNr].getTeam() == 0) {
                return true;
            }
        }
        return false;
    }

    public void putPiece(int columnsNr, int teamNr) {
        for (int i = rows-1; i >= 0; i--) {
            if (circles[i][columnsNr].getTeam() == 0) {
                circles[i][columnsNr].changeTeam(teamNr);
                break;
            }
        }
    }

    public void findInsert() {
        for (int i = 0; i < columns; i++) {
            if (clicked == insertButtons[i]) {
                chosenColumn = i;
            }
        }
    }

    public boolean checkWin() {
        return checkWinVertical() || checkWinHorizontal() || checkWinDiagonalUp() || checkWinDiagonalDown();
    }

    public boolean checkWinHorizontal() {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i+1][j].getTeam() == currentPlayer.getTeam() &&
                        circles[i+2][j].getTeam() == currentPlayer.getTeam() && circles[i+3][j].getTeam() == currentPlayer.getTeam()) {
                    return true;
                }
            }
        } return false;
    }

    public boolean checkWinVertical() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i][j+1].getTeam() == currentPlayer.getTeam() &&
                        circles[i][j+2].getTeam() == currentPlayer.getTeam() && circles[i][j+3].getTeam() == currentPlayer.getTeam()) {
                    return true;
                }
            }
        } return false;
    }

    public boolean checkWinDiagonalUp() {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i+1][j+1].getTeam() == currentPlayer.getTeam() &&
                        circles[i+2][j+2].getTeam() == currentPlayer.getTeam() && circles[i+3][j+3].getTeam() == currentPlayer.getTeam()) {
                    return true;
                }
            }
        } return false;
    }

    public boolean checkWinDiagonalDown() {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == currentPlayer.getTeam() && circles[i+1][j-1].getTeam() == currentPlayer.getTeam() &&
                        circles[i+2][j-2].getTeam() == currentPlayer.getTeam() && circles[i+3][j-3].getTeam() == currentPlayer.getTeam()) {
                    return true;
                }
            }
        } return false;
    }

    public boolean checkBoardFull() {
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

    public void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public void setMessages() {
        winMessage = currentPlayer.getName() + " wins! Score: "+player1.getName()+" "+player1.getScore()+", "+player2.getName()+" "+player2.getScore();
        drawMessage = "It's a draw! Score: "+player1.getName()+" "+player1.getScore()+". "+player2.getName()+" "+player2.getScore();
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
                JOptionPane.showMessageDialog(null, winMessage);
                newGame();
            } else if (checkBoardFull()) {
                JOptionPane.showMessageDialog(null, drawMessage);
                newGame();
            } else {
                changePlayer();
            }
        }
    }

}
