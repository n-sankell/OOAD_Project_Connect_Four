public class AI extends Player {

    private String name;

    public AI(int team) {
        super(team);
    }

    public void setName() {
        name = "AI";
    }

    public String getName() {
        return name;
    }

    public static int findBestMove(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < columns-1; j++) {
                if (circles[i][j-1].getTeam() == team && circles[i][j+1].getTeam() == team) {
                    return j;
                } else if (circles[i-1][j].getTeam() == team && circles[i+1][j].getTeam() == team) {
                    return j;
                } else if (circles[i+1][j].getTeam() == team || circles[i][j+1].getTeam() == team) {
                    return j;
                } else if (circles[rows-1][columns/2].getTeam() == 0 || circles[rows-2][columns/2].getTeam() == 0) {
                    return columns/2;
                }
            }
        }
        return columns/2;
    }

}
