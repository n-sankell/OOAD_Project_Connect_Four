package game;

public class GameLogic {

    private final int rows;
    private final int columns;
    private final GameController gameController;
    private final Piece[][] pieces;

    public GameLogic(GameController gameController) {
        this.gameController = gameController;
        this.rows = gameController.getRows();
        this.columns = gameController.getColumns();
        pieces = new Piece[rows][columns];
    }

    public void addNewPieces() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pieces[i][j] = new Piece(gameController.getPlayer1(), gameController.getPlayer2());
            }
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public boolean checkColumn(int columnsNr) {
        for (int i = rows - 1; i >= 0; i--) {
            if (pieces[i][columnsNr].getTeam() == 0) {
                return true;
            }
        }
        return false;
    }

    public void putPiece(int columnsNr, int teamNr) {
        for (int i = rows - 1; i >= 0; i--) {
            if (pieces[i][columnsNr].getTeam() == 0) {
                pieces[i][columnsNr].changeTeam(teamNr);
                break;
            }
        }
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

    public boolean isEmpty() {
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (pieces[i][j].getTeam() == 0) {
                    counter++;
                }
            }
        } return counter == rows * columns;
    }

    public boolean checkWin() {
        return checkWinVertical() || checkWinHorizontal() || checkWinDiagonalUp() || checkWinDiagonalDown();
    }

    private boolean checkWinHorizontal() {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (pieces[i][j].getTeam() == gameController.getCurrentTeam() && pieces[i + 1][j].getTeam() == gameController.getCurrentTeam() &&
                        pieces[i + 2][j].getTeam() == gameController.getCurrentTeam() && pieces[i + 3][j].getTeam() == gameController.getCurrentTeam()) {
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
                if (pieces[i][j].getTeam() == gameController.getCurrentTeam() && pieces[i][j + 1].getTeam() == gameController.getCurrentTeam() &&
                        pieces[i][j + 2].getTeam() == gameController.getCurrentTeam() && pieces[i][j + 3].getTeam() == gameController.getCurrentTeam()) {
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
                if (pieces[i][j].getTeam() == gameController.getCurrentTeam() && pieces[i + 1][j + 1].getTeam() == gameController.getCurrentTeam() &&
                        pieces[i + 2][j + 2].getTeam() == gameController.getCurrentTeam() && pieces[i + 3][j + 3].getTeam() == gameController.getCurrentTeam()) {
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
                if (pieces[i][j].getTeam() == gameController.getCurrentTeam() && pieces[i + 1][j - 1].getTeam() == gameController.getCurrentTeam() &&
                        pieces[i + 2][j - 2].getTeam() == gameController.getCurrentTeam() && pieces[i + 3][j - 3].getTeam() == gameController.getCurrentTeam()) {
                    setWinningColors(i, j, i + 1, j - 1, i + 2, j - 2, i + 3, j - 3);
                    return true;
                }
            }
        }
        return false;
    }

    private void setWinningColors(int a, int b, int c, int d, int e, int f, int g, int h) {
        pieces[a][b].winningPieces(gameController.getCurrentTeam());
        pieces[c][d].winningPieces(gameController.getCurrentTeam());
        pieces[e][f].winningPieces(gameController.getCurrentTeam());
        pieces[g][h].winningPieces(gameController.getCurrentTeam());
    }

}
