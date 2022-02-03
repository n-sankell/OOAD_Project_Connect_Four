package game;

import game.network.ClientConnection;
import gui.CustomJop;
import gui.GuiColors;
import packages.MovePackage;
import packages.NewRoundPackage;

import java.io.IOException;
import java.util.Random;

public class Board {

    private final int rows = 6;
    private final int columns = 7;
    private final GameMode gameMode;
    private PlayerTurn playerTurn = PlayerTurn.NOT_YOUR_TURN;
    private int roundCounter = 0;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private String winMessage;
    private String drawMessage;
    private ClientConnection connection;
    private BoardLogic logic;
    private GuiUpdateListener guiListener;

    public Board(Player player1, Player player2, GameMode gameMode) {
        this.gameMode = gameMode;
        this.player1 = player1;
        this.player2 = player2;
        compareColors();
        currentPlayer = player1;
        setMessages();
    }

    public void setLogic(BoardLogic logic) {
        this.logic = logic;
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

    public int getCurrentTeam() {
        return currentPlayer.getTeam();
    }

    public Piece[][] getCircles() {
        return logic.getCircles();
    }

    public void setGuiUpdateListener(GuiUpdateListener guiListener) {
        this.guiListener = guiListener;
    }

    public PlayerTurn getPlayerTurn() {
        return playerTurn;
    }

    public void newGame() {
        playerTurn = PlayerTurn.NOT_YOUR_TURN;
        roundCounter++;
        System.out.println("round "+roundCounter+" begins. PlayerTurn = "+playerTurn);
        logic.addCircles();
        if (roundCounter % 2 != 0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        if (gameMode == GameMode.ONE_PLAYER && currentPlayer == player2) {
            aiTurn();
        }
        if (gameMode == GameMode.NETWORK && roundCounter > 1) {
            connection.sendPackage(new NewRoundPackage(roundCounter));
        }
    }

    public void compareColors() {
        if (player1.getPlayerColor().equals(player2.getPlayerColor())) {
            if (player2.getPlayerColor().equals(GuiColors.PIECE_YELLOW)) {
                player2.setPlayerColor(GuiColors.ALT_PIECE_YELLOW);
            } else if (player2.getPlayerColor().equals(GuiColors.PIECE_RED)) {
                player2.setPlayerColor(GuiColors.ALT_PIECE_RED);
            } else if (player2.getPlayerColor().equals(GuiColors.PIECE_BLUE)) {
                player2.setPlayerColor(GuiColors.ALT_PIECE_BLUE);
            } else if (player2.getPlayerColor().equals(GuiColors.PIECE_GREEN)) {
                player2.setPlayerColor(GuiColors.ALT_PIECE_GREEN);
            }
        }
    }

    private void changePlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public boolean isEmpty() {
        return logic.isEmpty();
    }

    private void setMessages() {
        winMessage = currentPlayer.getName() + " WINS THE ROUND!" + "\nSCORE FOR " + player1.getName() + ": " +
                player1.getScore() + "\n" + "SCORE FOR " + player2.getName() + ": " + player2.getScore();
        drawMessage = "IT'S A DRAW!" + "\nSCORE FOR " + player1.getName() + ": " + player1.getScore() + "\n" +
                "SCORE FOR " + player2.getName() + ": " + player2.getScore();
    }

    private void showDrawMessage() {
        setMessages();
        try {
            new CustomJop(drawMessage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showWinMessage() {
        setMessages();
        try {
            new CustomJop(winMessage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void aiTurn() {
        while (true) {
            Random random = new Random();
            int aiRandomMove = random.nextInt(columns);
            AI ai = (AI) player2;
            int aiMove = ai.makeMove(logic.getCircles(), rows, columns);
            if (logic.checkColumn(aiMove)) {
                logic.putPiece(aiMove, currentPlayer.getTeam());
                break;
            } else if (logic.checkColumn(aiRandomMove)) {
                logic.putPiece(aiRandomMove, currentPlayer.getTeam());
                break;
            }
        }
        checkScoreFromOtherPlayer();
    }

    private void checkScoreFromOtherPlayer() {
        if (logic.checkWin()) {
            currentPlayer.setScore(1);
            showWinMessage();
            newGame();
        } else if (logic.checkBoardFull()) {
            showDrawMessage();
            newGame();
        } else {
            playerTurn = PlayerTurn.YOUR_TURN;
            changePlayer();
        }
    }

    public void setGameHandler() {
        connection.setGameEventListener((event, o) -> {
            switch (event) {
                case 4 -> {
                    playerTurn = PlayerTurn.NOT_YOUR_TURN;
                    MovePackage movePackage = (MovePackage) o;
                    int networkMove = (int) movePackage.getMove();
                    logic.putPiece(networkMove, currentPlayer.getTeam());
                    checkScoreFromOtherPlayer();
                    System.out.println("PlayerTurn = "+playerTurn+ " from move package - second");
                    guiListener.updateOccurred();
                }
                case 5 -> {
                    playerTurn = PlayerTurn.YOUR_TURN;
                    System.out.println("round "+roundCounter +" " +currentPlayer.getName()+"received a startPackage");
                    System.out.println("PlayerTurn = "+playerTurn+ " from start new game package");
                    guiListener.updateOccurred();
                }
                case 6 -> {
                    playerTurn = PlayerTurn.YOUR_TURN;
                    System.out.println(currentPlayer.getName()+"received a newRoundPackage "+ roundCounter);
                    System.out.println("PlayerTurn = "+playerTurn+ " from ");
                    guiListener.updateOccurred();
                }
            }
        });
    }

    private void checkWinOrFull() {
        if (logic.checkWin()) {
            currentPlayer.setScore(1);
            showWinMessage();
            newGame();
        } else if (logic.checkBoardFull()) {
            showDrawMessage();
            newGame();
        } else {
            changePlayer();
            if (gameMode == GameMode.ONE_PLAYER) {
                aiTurn();
            }
        }
    }

    public void makeMove(int chosenColumn) {
        if (logic.checkColumn(chosenColumn)) {
            if (gameMode == GameMode.NETWORK && playerTurn == PlayerTurn.YOUR_TURN) {
                playerTurn = PlayerTurn.NOT_YOUR_TURN;
                logic.putPiece(chosenColumn, currentPlayer.getTeam());
                connection.sendPackage(new MovePackage(chosenColumn));
                checkWinOrFull();
            } if (gameMode == GameMode.ONE_PLAYER || gameMode == GameMode.TWO_PLAYERS) {
                logic.putPiece(chosenColumn, currentPlayer.getTeam());
                checkWinOrFull();
            }
        }
    }
}
