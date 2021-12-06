public class Piece {
    private int xPos;
    private int yPos;
    private int team; // 0: no team, 1: player 1, 2: player 2;

    public Piece(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.team = 0;
    }

    public int getTeam() {
        return team;
    }

    public void changeTeam(int teamTo){
        this.team = teamTo;
    }
}
