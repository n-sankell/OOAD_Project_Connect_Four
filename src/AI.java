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

    public static int findMove(Piece[][] circles, int rows, int columns, int team) {
        if (findWinningMove(circles, rows, columns, team) != 999) {
            return findWinningMove(circles, rows, columns, team);
        } else if (blockPlayerVertical(circles, rows, columns, team) != 999) {
            return blockPlayerVertical(circles, rows, columns, team);
        } else {
            return findBasicMove(circles, rows, columns, team);
        }
    }

    public static int blockPlayerVertical(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i-1][j].getTeam() == 1 &&
                        circles[i-2][j].getTeam() == 1 && circles[i-3][j].getTeam() == 0) {
                    return j;
                }
            }
        }
        return 999;
    }

    public static int findWinningMove(Piece[][] circles, int rows, int columns, int team) {

        return 999;
    }

    public static int findBasicMove(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < columns-1; j++) {
                if (circles[i][j-1].getTeam() == team && circles[i][j+1].getTeam() == team) {
                    return j;
                } else if (circles[3][columns/2].getTeam() == 1) {
                    return j+1;
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
