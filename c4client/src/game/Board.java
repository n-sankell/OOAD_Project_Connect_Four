package game;

import game.network.ClientConnection;
import gui.CustomJop;
import packages.MovePackage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Board {

    private final int rows = 6;
    private final int columns = 7;
    private final GameMode gameMode;
    private int roundCounter = 0;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private String winMessage;
    private String drawMessage;
    private ClientConnection connection;
    private final Piece[][] circles = new Piece[rows][columns];

    public Board(Player player1, Player player2, GameMode gameMode) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        this.gameMode = gameMode;
        compareColors();
        setMessages();
        newGame();
    }

    public void setConnection(ClientConnection connection) {
        this.connection = connection;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Piece[][] getCircles() {
        return circles;
    }

    public void newGame() {
        roundCounter++;
        addCircles();
        if (roundCounter % 2 == 0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        if (gameMode == GameMode.ONE_PLAYER && currentPlayer == player2) {
            aiTurn();
        }
    }

    public void compareColors() {
        if (player1.getPlayerColor() == player2.getPlayerColor()) {
            Color darkerPlayerTwo = player2.getPlayerColor().darker();
            player2.setPlayerColor(darkerPlayerTwo);
        }
    }

    private void addCircles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                circles[i][j] = new Piece(player1,player2);
            }
        }
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

    public boolean isEmpty() {
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0) {
                    counter++;
                }
            }
        } return counter == rows * columns;
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
            AI ai = (AI) player2;
            int aiMove = ai.makeMove(circles, rows, columns);
            if (checkColumn(aiMove)) {
                putPiece(aiMove, currentPlayer.getTeam());
                break;
            } else if (checkColumn(aiRandomMove)) {
                putPiece(aiRandomMove, currentPlayer.getTeam());
                break;
            }
        }
        checkOtherScore();
    }

    private void checkOtherScore() {
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

    private void networkMove(int chosenColumn) {
        currentPlayer = player2;
        connection.sendPackage(new MovePackage(chosenColumn));
        connection.setGameEventListener((event, o) -> {
            if (event == 4) {
                MovePackage movePackage = (MovePackage) o;
                int networkMove = (int) movePackage.getMove();
                if (checkColumn(networkMove)) {
                    putPiece(networkMove, currentPlayer.getTeam());
                }
            }
        });
        checkOtherScore();
    }

    public void makeMove(int chosenColumn) {
        if (checkColumn(chosenColumn)) {
            putPiece(chosenColumn, currentPlayer.getTeam());
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
                if (gameMode == GameMode.ONE_PLAYER) {
                    aiTurn();
                } else if (gameMode == GameMode.TWO_PLAYERS) {
                    changePlayer();
                } else if (gameMode == GameMode.NETWORK) {
                    networkMove(chosenColumn);
                }
            }
        }
    }
}
