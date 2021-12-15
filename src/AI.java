import java.awt.*;

public class AI extends Player {

    private static final String NAME = "AI";
    private final int difficulty;

    public AI(int team, Color playerColor, int difficulty) {
        super(team, playerColor);
        this.difficulty = difficulty;
    }

    public String getName() {
        return NAME;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public static int findMove(Piece[][] circles, int rows, int columns, int ai, int difficulty) {
        if (findWinningMoveVertical(circles, rows, columns, ai) != 999) {
            return findWinningMoveVertical(circles, rows, columns, ai);
        } else if (findWinningMoveHorizontalA(circles, rows, columns, ai) != 999) {
            return findWinningMoveHorizontalA(circles, rows, columns, ai);
        } else if (findWinningMoveHorizontalB(circles, rows, columns, ai) != 999) {
            return findWinningMoveHorizontalB(circles, rows, columns, ai);
        } else if (findWinningMoveHorizontalC(circles, rows, columns, ai) != 999) {
            return findWinningMoveHorizontalC(circles, rows, columns, ai);
        } else if (findWinningMoveHorizontalD(circles, rows, columns, ai) != 999) {
            return findWinningMoveHorizontalD(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalUpA(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalUpA(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalUpB(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalUpB(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalUpC(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalUpC(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalUpD(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalUpD(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalDownA(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalDownA(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalDownB(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalDownB(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalDownC(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalDownC(circles, rows, columns, ai);
        } else if (findWinningMoveDiagonalDownD(circles, rows, columns, ai) != 999) {
            return findWinningMoveDiagonalDownD(circles, rows, columns, ai);
        } else if (blockPlayerVertical(circles, rows, columns) != 999) {
            return blockPlayerVertical(circles, rows, columns);
        } else if (blockPlayerHorizontalA(circles, rows, columns) != 999) {
            return blockPlayerHorizontalA(circles, rows, columns);
        } else if (blockPlayerHorizontalB(circles, rows, columns) != 999) {
            return blockPlayerHorizontalB(circles, rows, columns);
        } else if (blockPlayerHorizontalC(circles, rows, columns) != 999) {
            return blockPlayerHorizontalC(circles, rows, columns);
        } else if (blockPlayerHorizontalD(circles, rows, columns) != 999) {
            return blockPlayerHorizontalD(circles, rows, columns);
        } else if (blockPlayerDiagonalUpA(circles, rows, columns) != 999) {
            return blockPlayerDiagonalUpA(circles, rows, columns);
        } else if (blockPlayerDiagonalUpB(circles, rows, columns) != 999) {
            return blockPlayerDiagonalUpB(circles, rows, columns);
        } else if (blockPlayerDiagonalUpC(circles, rows, columns) != 999) {
            return blockPlayerDiagonalUpC(circles, rows, columns);
        } else if (blockPlayerDiagonalUpD(circles, rows, columns) != 999) {
            return blockPlayerDiagonalUpD(circles, rows, columns);
        } else if (blockPlayerDiagonalDownA(circles, rows, columns) != 999) {
            return blockPlayerDiagonalDownA(circles, rows, columns);
        } else if (blockPlayerDiagonalDownB(circles, rows, columns) != 999) {
            return blockPlayerDiagonalDownB(circles, rows, columns);
        } else if (blockPlayerDiagonalDownC(circles, rows, columns) != 999) {
            return blockPlayerDiagonalDownC(circles, rows, columns);
        } else if (blockPlayerDiagonalDownD(circles, rows, columns) != 999) {
            return blockPlayerDiagonalDownD(circles, rows, columns);
        } else if (blockPlayerHorizontalBuildUpA(circles, rows, columns) != 999 && difficulty > 1) {
            return blockPlayerHorizontalBuildUpA(circles, rows, columns);
        } else if (blockPlayerHorizontalBuildUpB(circles, rows, columns) != 999 && difficulty > 1) {
            return blockPlayerHorizontalBuildUpB(circles, rows, columns);
        } else if (blockPlayerHorizontalBuildUpC(circles, rows, columns) != 999 && difficulty > 1) {
            return blockPlayerHorizontalBuildUpC(circles, rows, columns);
        } else if (blockPlayerDiagonalBuildUpA(circles, rows, columns) != 999 && difficulty > 1) {
            return blockPlayerDiagonalBuildUpA(circles, rows, columns);
        } else if (blockPlayerDiagonalBuildUpB(circles, rows, columns) != 999 && difficulty > 1) {
            return blockPlayerDiagonalBuildUpB(circles, rows, columns);
        } else if (dontEnablePlayerHorizontalA(circles, rows, columns) != 999 && difficulty > 1) {
            return findBasicMove(circles, rows, columns, ai); //TODO add method that excludes the returned value (putAnywhereButHere)
        } else if (dontEnablePlayerHorizontalB(circles, rows, columns) != 999 && difficulty > 1) {
            return findBasicMove(circles, rows, columns, ai);
        } else if (dontEnablePlayerHorizontalC(circles, rows, columns) != 999 && difficulty > 1) {
            return findBasicMove(circles, rows, columns, ai);
        } else if (dontEnablePlayerHorizontalD(circles, rows, columns) != 999 && difficulty > 1) {
            return findBasicMove(circles, rows, columns, ai);
        } else if (buildUpHorizontalA(circles, rows, columns) != 999 && difficulty > 1) {
            return buildUpHorizontalA(circles, rows, columns);
        } else if (buildUpHorizontalB(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return buildUpHorizontalB(circles, rows, columns, ai);
        } else if (buildUpHorizontalC(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return buildUpHorizontalC(circles, rows, columns, ai);
        } else if (buildUpHorizontalD(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return buildUpHorizontalD(circles, rows, columns, ai);
        } else if (buildUpVertical(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return buildUpVertical(circles, rows, columns, ai);
        } else if (firstBuildUpHorizontalA(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return firstBuildUpHorizontalA(circles, rows, columns, ai);
        } else if (firstBuildUpHorizontalB(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return firstBuildUpHorizontalB(circles, rows, columns, ai);
        } else if (firstBuildUpHorizontalC(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return firstBuildUpHorizontalC(circles, rows, columns, ai);
        } else if (firstBuildUpVertical(circles, rows, columns, ai) != 999 && difficulty > 1) {
            return firstBuildUpVertical(circles, rows, columns, ai);
        } else {
            return findBasicMove(circles, rows, columns, ai);
        }
    }

    private static int findWinningMoveVertical(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == ai &&
                        circles[i+2][j].getTeam() == ai && circles[i+3][j].getTeam() == ai) {
                    return j;
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveHorizontalA(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == ai &&
                        circles[i][j+2].getTeam() == ai && circles[i][j+3].getTeam() == ai) {
                    if (i != rows-1 && circles[i+1][j].getTeam() != 0) {
                        return j;
                    } else if (i == rows-1) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveHorizontalB(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == ai && circles[i][j+3].getTeam() == ai) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveHorizontalC(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == ai &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == ai) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        return j+2;
                    } else if (i == rows-1) {
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveHorizontalD(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == ai &&
                        circles[i][j+2].getTeam() == ai && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+3].getTeam() != 0) {
                        return j+3;
                    } else if (i == rows-1) {
                        return j + 3;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalUpA(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j+1].getTeam() == ai &&
                        circles[i+2][j+2].getTeam() == ai && circles[i+3][j+3].getTeam() == ai) {
                    if (circles[i+1][j].getTeam() != 0) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalUpB(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i+1][j+1].getTeam() == 0 &&
                        circles[i+2][j+2].getTeam() == ai && circles[i+3][j+3].getTeam() == ai) {
                    if (circles[i+2][j+1].getTeam() != 0) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalUpC(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i+1][j+1].getTeam() == ai &&
                        circles[i+2][j+2].getTeam() == 0 && circles[i+3][j+3].getTeam() == ai) {
                    if (circles[i+3][j+2].getTeam() != 0) {
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalUpD(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i+1][j+1].getTeam() == ai &&
                        circles[i+2][j+2].getTeam() == ai && circles[i+3][j+3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        return j + 3;
                    } else if (circles[i+4][j+3].getTeam() != 0) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalDownA(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j-1].getTeam() == ai &&
                        circles[i+2][j-2].getTeam() == ai && circles[i+3][j-3].getTeam() == ai) {
                    if (circles[i+1][j].getTeam() != 0) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalDownB(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == ai && circles[i+1][j-1].getTeam() == 0 &&
                        circles[i+2][j-2].getTeam() == ai && circles[i+3][j-3].getTeam() == ai) {
                    if (circles[i+2][j-1].getTeam() != 0) {
                        return j-1;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalDownC(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == ai && circles[i+1][j-1].getTeam() == ai &&
                        circles[i+2][j-2].getTeam() == 0 && circles[i+3][j-3].getTeam() == ai) {
                    if (circles[i+3][j-2].getTeam() != 0) {
                        return j-2;
                    }
                }
            }
        }
        return 999;
    }

    private static int findWinningMoveDiagonalDownD(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == ai && circles[i+1][j-1].getTeam() == ai &&
                        circles[i+2][j-2].getTeam() == ai && circles[i+3][j-3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        return j-3;
                    } else if (circles[i+4][j-3].getTeam() != 0) {
                        return j-3;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerVertical(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == 1 &&
                        circles[i+2][j].getTeam() == 1 && circles[i+3][j].getTeam() == 1) {
                    return j;
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 1) {
                    if (i != rows-1 && circles[i+1][j].getTeam() != 0) {
                        return j;
                    } else if (i == rows-1) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalB(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 1) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalC(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == 1) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        return j+2;
                    } else if (i == rows-1) {
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalD(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+3].getTeam() != 0) {
                        return j+3;
                    } else if (i == rows-1) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalUpA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 1) {
                    if (circles[i+1][j].getTeam() != 0) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalUpB(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j+1].getTeam() == 0 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 1) {
                    if (circles[i+2][j+1].getTeam() != 0) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalUpC(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 0 && circles[i+3][j+3].getTeam() == 1) {
                    if (circles[i+3][j+2].getTeam() != 0) {
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalUpD(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        return j+3;
                    } else if (i+3 < rows-1 && circles[i+4][j+3].getTeam() != 0) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalDownA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 1) {
                    if (circles[i+1][j].getTeam() != 0) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalDownB(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j-1].getTeam() == 0 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 1) {
                    if (circles[i+2][j-1].getTeam() != 0) {
                        return j-1;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalDownC(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 0 && circles[i+3][j-3].getTeam() == 1) {
                    if (circles[i+3][j-2].getTeam() != 0) {
                        return j-2;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalDownD(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        return j-3;
                    } else if (i+3 < rows-1 && circles[i+4][j-3].getTeam() != 0) {
                        return j-3;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalBuildUpA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+3].getTeam() != 0) {
                        return j+3;
                    } else if (i == rows-1) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalBuildUpB(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerHorizontalBuildUpC(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        return j+2;
                    } else if (i == rows-1) {
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalBuildUpA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        return j-3;
                    } else if (circles[i+4][j-3].getTeam() != 0) {
                        return j-3;
                    }
                }
            }
        }
        return 999;
    }

    private static int blockPlayerDiagonalBuildUpB(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        return j+3;
                    } else if (circles[i+4][j+3].getTeam() != 0) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    private static int dontEnablePlayerHorizontalA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i < rows-2 && circles[i+2][j+3].getTeam() != 0 && circles[i+1][j+3].getTeam() == 0) {
                        return j+3;
                    } else if (i == rows-2 && circles[i+1][j+3].getTeam() == 0) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    private static int dontEnablePlayerHorizontalB(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 1) {
                    if (i < rows-2 && circles[i+2][j].getTeam() != 0 && circles[i+1][j].getTeam() == 0) {
                        return j;
                    } else if (i == rows-2 && circles[i+1][j].getTeam() == 0) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int dontEnablePlayerHorizontalC(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-2; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1) {
                    if (i < rows-2 && circles[i+2][j].getTeam() != 0 && circles[i+1][j].getTeam() == 0) {
                        return j;
                    } else if (i == rows-2 && circles[i+1][j].getTeam() == 0) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int dontEnablePlayerHorizontalD(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-2; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 1) {
                    if (i < rows-2 && circles[i+2][j+1].getTeam() != 0 && circles[i+1][j+1].getTeam() == 0) {
                        return j+1;
                    } else if (i == rows-2 && circles[i+1][j+1].getTeam() == 0) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int buildUpHorizontalA(Piece[][] circles, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        return j;
                    } else if (i == rows-1) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int buildUpHorizontalB(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == ai) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int buildUpHorizontalC(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == ai &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        return j+2;
                    } else if (i == rows-1) {
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    private static int buildUpHorizontalD(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == ai && circles[i][j+3].getTeam() == ai) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int firstBuildUpHorizontalA(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-2; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == ai) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int firstBuildUpHorizontalB(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-2; j++) {
                if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        return j+1;
                    } else if (i == rows-1) {
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    private static int firstBuildUpHorizontalC(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-2; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == ai &&
                        circles[i][j+2].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j].getTeam() != 0) {
                        return j;
                    } else if (i == rows-1) {
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    private static int buildUpVertical(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-2; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == ai && circles[i+2][j].getTeam() == ai) {
                    return j;
                }
            }
        }
        return 999;
    }
    
    private static int firstBuildUpVertical(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == ai) {
                    return j;
                }
            }
        }
        return 999;
    }

    private static int findBasicMove(Piece[][] circles, int rows, int columns, int ai) {
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < columns-1; j++) {
                if (circles[i][j].getTeam() == ai && circles[i + 1][j].getTeam() == ai) {
                    return j;
                } else if (circles[i][j].getTeam() == ai && circles[i][j+1].getTeam() == 0) {
                    return j+1;
                } else if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == ai) {
                    return j;
                } else if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == ai) {
                    return j;
                }
            }
        }
        return columns / 2;
    }

}
