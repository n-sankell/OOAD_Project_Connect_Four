public class Board {
    private Piece[][] board = new Piece[6][7];

    public Board(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new Piece(i, j);
            }
        }
    }

    public Piece getPiece(int x, int y){
        return board[x][y];
    }
    public int getTeam(int x, int y){
        return board[x][y].getTeam();
    }

    public void resetBoard(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j].changeTeam(0);
            }
        }
    }
}
