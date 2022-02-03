package game;

public class BoardLogic {

    private final int rows;
    private final int columns;
    private final Board board;
    private final Piece[][] circles;

    public BoardLogic(Board board) {
        this.board = board;
        this.rows = board.getRows();
        this.columns = board.getColumns();
        circles = new Piece[rows][columns];
    }

    public void addCircles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                circles[i][j] = new Piece(board.getPlayer1(), board.getPlayer2());
            }
        }
    }

    public Piece[][] getCircles() {
        return circles;
    }

    public boolean checkColumn(int columnsNr) {
        for (int i = rows - 1; i >= 0; i--) {
            if (circles[i][columnsNr].getTeam() == 0) {
                return true;
            }
        }
        return false;
    }

    public void putPiece(int columnsNr, int teamNr) {
        for (int i = rows - 1; i >= 0; i--) {
            if (circles[i][columnsNr].getTeam() == 0) {
                circles[i][columnsNr].changeTeam(teamNr);
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
                if (circles[i][j].getTeam() == 0) {
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
                if (circles[i][j].getTeam() == board.getCurrentTeam() && circles[i + 1][j].getTeam() == board.getCurrentTeam() &&
                        circles[i + 2][j].getTeam() == board.getCurrentTeam() && circles[i + 3][j].getTeam() == board.getCurrentTeam()) {
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
                if (circles[i][j].getTeam() == board.getCurrentTeam() && circles[i][j + 1].getTeam() == board.getCurrentTeam() &&
                        circles[i][j + 2].getTeam() == board.getCurrentTeam() && circles[i][j + 3].getTeam() == board.getCurrentTeam()) {
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
                if (circles[i][j].getTeam() == board.getCurrentTeam() && circles[i + 1][j + 1].getTeam() == board.getCurrentTeam() &&
                        circles[i + 2][j + 2].getTeam() == board.getCurrentTeam() && circles[i + 3][j + 3].getTeam() == board.getCurrentTeam()) {
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
                if (circles[i][j].getTeam() == board.getCurrentTeam() && circles[i + 1][j - 1].getTeam() == board.getCurrentTeam() &&
                        circles[i + 2][j - 2].getTeam() == board.getCurrentTeam() && circles[i + 3][j - 3].getTeam() == board.getCurrentTeam()) {
                    setWinningColors(i, j, i + 1, j - 1, i + 2, j - 2, i + 3, j - 3);
                    return true;
                }
            }
        }
        return false;
    }

    private void setWinningColors(int a, int b, int c, int d, int e, int f, int g, int h) {
        circles[a][b].winningPieces(board.getCurrentTeam());
        circles[c][d].winningPieces(board.getCurrentTeam());
        circles[e][f].winningPieces(board.getCurrentTeam());
        circles[g][h].winningPieces(board.getCurrentTeam());
    }

}
