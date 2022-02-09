package gui;

import game.GameController;
import game.enums.GameMode;
import game.enums.PlayerTurn;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class GuiBoard extends JPanel implements ActionListener {
    private final GameController gameController;
    private final int rows;
    private final int columns;
    private int chosenColumn;
    private String winMessage;
    private String drawMessage;
    private JTextPane status;
    private JLabel scorePlayerOne;
    private JLabel scorePlayerTwo;
    private JButton[] insertButtons;
    private JButton clicked = new JButton();
    private final JPanel insertPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JPanel statusPanel = new JPanel();
    private ImageIcon insertButtonImage;

    public GuiBoard(GameController gameController) {
        this.gameController = gameController;
        this.rows = gameController.getRows();
        this.columns = gameController.getColumns();
        setInsertButtonImage();
        compareColors();
        setUpInsertButtons();
        setUpBasePanel();
        setUpdateHandler();
        setUpStatusPanel();
    }

    public void start() {
        gameController.newGame();
        setStatusPanelTexts();
        addInsertButtons();
        addBasePanel();
        updateBoard();
        addStatusPanel();
        repaint();
        revalidate();
    }

    private void setInsertButtonImage() {
        insertButtonImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/insert_button.png")));
    }

    public void setUpdateHandler() {
        gameController.setGuiUpdateListener((event) -> {
            switch (event) {
                case 1 -> {
                    updateBoard();
                    updateStatusPanel();
                }
                case 2 -> {
                    setWinAndDrawMessages();
                    showWinMessage();
                }
                case 3 -> {
                    setWinAndDrawMessages();
                    showDrawMessage();
                }
            }

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
        addPieces();
        repaint();
        revalidate();
    }

    private void addPieces() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boardPanel.add(gameController.getPieces()[i][j]);
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
        scorePlayerOne.setForeground(gameController.getPlayer1().getPlayerColor());
        status = new JTextPane();
        status.setFont(new Font("Druk Wide",Font.BOLD,20));
        StyledDocument documentStyle = status.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        status.setForeground(gameController.getCurrentPlayer().getPlayerColor().brighter());
        status.setBackground(Color.BLACK);
        scorePlayerTwo = new JLabel();
        scorePlayerTwo.setFont(new Font("Druk Wide",Font.BOLD,15));
        scorePlayerTwo.setHorizontalAlignment(SwingConstants.LEFT);
        scorePlayerTwo.setForeground(gameController.getPlayer2().getPlayerColor());
    }

    public void setStatusPanelTexts() {
        scorePlayerOne.setText("SCORE "+ gameController.getPlayer1Name()+": "+ gameController.getPlayer1Score());
        status.setText(getStatusText());
        status.setForeground(gameController.getCurrentPlayer().getPlayerColor().brighter());
        scorePlayerTwo.setText("SCORE "+ gameController.getPlayer2Name()+": "+ gameController.getPlayer2Score());
    }

    public void compareColors() {
        if (gameController.getPlayer1().getPlayerColor().equals(gameController.getPlayer2().getPlayerColor())) {
            if (gameController.getPlayer2().getPlayerColor().equals(GuiColors.PIECE_YELLOW)) {
                gameController.getPlayer2().setPlayerColor(GuiColors.ALT_PIECE_YELLOW);
            } else if (gameController.getPlayer2().getPlayerColor().equals(GuiColors.PIECE_RED)) {
                gameController.getPlayer2().setPlayerColor(GuiColors.ALT_PIECE_RED);
            } else if (gameController.getPlayer2().getPlayerColor().equals(GuiColors.PIECE_BLUE)) {
                gameController.getPlayer2().setPlayerColor(GuiColors.ALT_PIECE_BLUE);
            } else if (gameController.getPlayer2().getPlayerColor().equals(GuiColors.PIECE_GREEN)) {
                gameController.getPlayer2().setPlayerColor(GuiColors.ALT_PIECE_GREEN);
            }
        }
    }

    private String getStatusText() {
        if (gameController.getGameMode() == GameMode.NETWORK && gameController.getPlayerTurn() == PlayerTurn.NOT_YOUR_TURN && gameController.isEmpty()) {
            return "WAIT FOR " + gameController.getCurrentPlayerName()+" TO BEGIN!";
        } else if (gameController.isEmpty()) {
            return gameController.getCurrentPlayerName()+" BEGINS!";
        } else if (gameController.getGameMode() == GameMode.ONE_PLAYER) {
            return "";
        } else if (gameController.getGameMode() == GameMode.NETWORK && gameController.getPlayerTurn() == PlayerTurn.NOT_YOUR_TURN) {
            return "WAIT FOR "+ gameController.getCurrentPlayerName()+"'S MOVE!";
        } else {
            return "YOUR TURN "+ gameController.getCurrentPlayerName()+"!";
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

    private void setWinAndDrawMessages() {
        winMessage = gameController.getCurrentPlayerName() + " WINS THE ROUND!" + "\nSCORE FOR " + gameController.getPlayer1Name() + ": " +
                gameController.getPlayer1Score() + "\n" + "SCORE FOR " + gameController.getPlayer2Name() + ": " + gameController.getPlayer2Score();
        drawMessage = "IT'S A DRAW!" + "\nSCORE FOR " + gameController.getPlayer1Name() + ": " + gameController.getPlayer1Score() + "\n" +
                "SCORE FOR " + gameController.getPlayer2Name() + ": " + gameController.getPlayer2Score();
    }

    private void showDrawMessage() {
        setWinAndDrawMessages();
        try {
            new CustomJop(drawMessage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showWinMessage() {
        setWinAndDrawMessages();
        try {
            new CustomJop(winMessage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        gameController.makeMove(findInsert());
        updateStatusPanel();
        updateBoard();
        repaint();
        revalidate();
    }

}
