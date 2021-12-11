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

    public static int findMove(Piece[][] circles, int rows, int columns, int ai) {
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
        } else if (blockPlayerVertical(circles, rows, columns, ai) != 999) {
            return blockPlayerVertical(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalA(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalA(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalB(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalB(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalC(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalC(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalD(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalD(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalUpA(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalUpA(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalUpB(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalUpB(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalUpC(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalUpC(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalUpD(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalUpD(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalDownA(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalDownA(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalDownB(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalDownB(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalDownC(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalDownC(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalDownD(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalDownD(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalBuildUpA(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalBuildUpA(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalBuildUpB(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalBuildUpB(circles, rows, columns, ai);
        } else if (blockPlayerHorizontalBuildUpC(circles, rows, columns, ai) != 999) {
            return blockPlayerHorizontalBuildUpC(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalBuildUpA(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalBuildUpA(circles, rows, columns, ai);
        } else if (blockPlayerDiagonalBuildUpB(circles, rows, columns, ai) != 999) {
            return blockPlayerDiagonalBuildUpB(circles, rows, columns, ai);
        } else if (buildUpHorizontalA(circles, rows, columns, ai) != 999) {
            return buildUpHorizontalA(circles, rows, columns, ai);
        } else if (buildUpHorizontalB(circles, rows, columns, ai) != 999) {
            return buildUpHorizontalB(circles, rows, columns, ai);
        } else if (buildUpHorizontalC(circles, rows, columns, ai) != 999) {
            return buildUpHorizontalC(circles, rows, columns, ai);
        } else if (buildUpHorizontalD(circles, rows, columns, ai) != 999) {
            return buildUpHorizontalD(circles, rows, columns, ai);
        } else if (buildUpVertical(circles, rows, columns, ai) != 999) {
            return buildUpVertical(circles, rows, columns, ai);
        } else {
            return findBasicMove(circles, rows, columns, ai);
        }
    }

    public static int findWinningMoveVertical(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == team &&
                        circles[i+2][j].getTeam() == team && circles[i+3][j].getTeam() == team) {
                    System.out.println("FindWinningMoveVertical");
                    return j;
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveHorizontalA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == team &&
                        circles[i][j+2].getTeam() == team && circles[i][j+3].getTeam() == team) {
                    if (i != rows-1 && circles[i+1][j].getTeam() != 0) {
                        System.out.println("FindWinningMoveHorizontalA");
                        return j;
                    } else if (i == rows-1) {
                        System.out.println("FindWinningMoveHorizontalA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveHorizontalB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == team && circles[i][j+3].getTeam() == team) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        System.out.println("FindWinningMoveHorizontalA");
                        return j+1;
                    } else if (i == rows-1) {
                        System.out.println("FindWinningMoveHorizontalA");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveHorizontalC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i][j+1].getTeam() == team &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == team) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        System.out.println("FindWinningMoveHorizontalA");
                        return j+2;
                    } else if (i == rows-1) {
                        System.out.println("FindWinningMoveHorizontalA");
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveHorizontalD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i][j+1].getTeam() == team &&
                        circles[i][j+2].getTeam() == team && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+3].getTeam() != 0) {
                        System.out.println("FindWinningMoveHorizontalB");
                        return j+3;
                    } else if (i == rows-1) {
                        System.out.println("FindWinningMoveHorizontalB");
                        return j + 3;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalUpA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j+1].getTeam() == team &&
                        circles[i+2][j+2].getTeam() == team && circles[i+3][j+3].getTeam() == team) {
                    if (circles[i+1][j].getTeam() != 0) {
                        System.out.println("findWinningMoveDiagonalUpA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalUpB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i+1][j+1].getTeam() == 0 &&
                        circles[i+2][j+2].getTeam() == team && circles[i+3][j+3].getTeam() == team) {
                    if (circles[i+2][j+1].getTeam() != 0) {
                        System.out.println("findWinningMoveDiagonalUpB");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalUpC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i+1][j+1].getTeam() == team &&
                        circles[i+2][j+2].getTeam() == 0 && circles[i+3][j+3].getTeam() == team) {
                    if (circles[i+3][j+2].getTeam() != 0) {
                        System.out.println("findWinningMoveDiagonalUpC");
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalUpD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i+1][j+1].getTeam() == team &&
                        circles[i+2][j+2].getTeam() == team && circles[i+3][j+3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        System.out.println("findWinningDiagonalUpD");
                        return j + 3;
                    } else if (circles[i+4][j+3].getTeam() != 0) {
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalDownA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j-1].getTeam() == team &&
                        circles[i+2][j-2].getTeam() == team && circles[i+3][j-3].getTeam() == team) {
                    if (circles[i+1][j].getTeam() != 0) {
                        System.out.println("findWinningMoveDiagonalDownA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalDownB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == team && circles[i+1][j-1].getTeam() == 0 &&
                        circles[i+2][j-2].getTeam() == team && circles[i+3][j-3].getTeam() == team) {
                    if (circles[i+2][j-1].getTeam() != 0) {
                        System.out.println("findWinningMoveDiagonalDownB");
                        return j-1;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalDownC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == team && circles[i+1][j-1].getTeam() == team &&
                        circles[i+2][j-2].getTeam() == 0 && circles[i+3][j-3].getTeam() == team) {
                    if (circles[i+3][j-2].getTeam() != 0) {
                        System.out.println("findWinningMoveDiagonalDownC");
                        return j-2;
                    }
                }
            }
        }
        return 999;
    }

    public static int findWinningMoveDiagonalDownD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == team && circles[i+1][j-1].getTeam() == team &&
                        circles[i+2][j-2].getTeam() == team && circles[i+3][j-3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        System.out.println("findWinningDiagonalDownD");
                        return j-3;
                    } else if (circles[i+4][j-3].getTeam() != 0) {
                        return j-3;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerVertical(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == 1 &&
                        circles[i+2][j].getTeam() == 1 && circles[i+3][j].getTeam() == 1) {
                    System.out.println("blockPlayerVertical");
                    return j;
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 1) {
                    if (i != rows-1 && circles[i+1][j].getTeam() != 0) {
                        System.out.println("BlockPlayerHorizontalA");
                        return j;
                    } else if (i == rows-1) {
                        System.out.println("BlockPlayerHorizontalA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 1) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        System.out.println("BlockPlayerHorizontalB");
                        return j+1;
                    } else if (i == rows-1) {
                        System.out.println("BlockPlayerHorizontalB");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == 1) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        System.out.println("blockPlayerHorizontalC");
                        return j+2;
                    } else if (i == rows-1) {
                        System.out.println("blockPlayerHorizontalC");
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+3].getTeam() != 0) {
                        System.out.println("blockPlayerHorizontalD");
                        return j+3;
                    } else if (i == rows-1) {
                        System.out.println("blockPlayerHorizontalD");
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalUpA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 1) {
                    if (circles[i+1][j].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalUpA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalUpB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j+1].getTeam() == 0 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 1) {
                    if (circles[i+2][j+1].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalUpB");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalUpC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 0 && circles[i+3][j+3].getTeam() == 1) {
                    if (circles[i+3][j+2].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalUpC");
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalUpD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        System.out.println("blockPlayerDiagonalUpD");
                        return j+3;
                    } else if (circles[i+4][j+3].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalUpD --- RISKY");
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalDownA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 1) {
                    if (circles[i+1][j].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalDownA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalDownB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j-1].getTeam() == 0 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 1) {
                    if (circles[i+2][j-1].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalDownB");
                        return j-1;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalDownC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 0 && circles[i+3][j-3].getTeam() == 1) {
                    if (circles[i+3][j-2].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalDownC");
                        return j-2;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalDownD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        System.out.println("blockPlayerDiagonalDownD");
                        return j-3;
                    } else if (circles[i+4][j-3].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalDownD ---- RISKY");
                        return j-3;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalBuildUpA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+3].getTeam() != 0) {
                        System.out.println("blockPlayerBuildUpA");
                        return j+3;
                    } else if (i == rows-1) {
                        System.out.println("blockPlayerBuildUpA");
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalBuildUpB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        System.out.println("blockPlayerBuildUpB");
                        return j+1;
                    } else if (i == rows-1) {
                        System.out.println("blockPlayerBuildUpB");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerHorizontalBuildUpC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 1 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        System.out.println("blockPlayerBuildUpC");
                        return j+2;
                    } else if (i == rows-1) {
                        System.out.println("blockPlayerBuildUpC");
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalBuildUpA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j-1].getTeam() == 1 &&
                        circles[i+2][j-2].getTeam() == 1 && circles[i+3][j-3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        System.out.println("blockPlayerDiagonalBuildupA");
                        return j-3;
                    } else if (circles[i+4][j-3].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalBuildupA --- RISKY");
                        return j-3;
                    }
                }
            }
        }
        return 999;
    }

    public static int blockPlayerDiagonalBuildUpB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-3; i++) {
            for (int j = 3; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j+1].getTeam() == 1 &&
                        circles[i+2][j+2].getTeam() == 1 && circles[i+3][j+3].getTeam() == 0) {
                    if (i+3 == rows-1) {
                        System.out.println("blockPlayerDiagonalBuildupB");
                        return j+3;
                    } else if (circles[i+4][j-3].getTeam() != 0) {
                        System.out.println("blockPlayerDiagonalBuildupB --- RISKY");
                        return j+3;
                    }
                }
            }
        }
        return 999;
    }

    public static int buildUpHorizontalA(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 1 &&
                        circles[i][j+2].getTeam() == 1 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i][j+2].getTeam() != 0) {
                        System.out.println("buildUpHorizontalA");
                        return j;
                    } else if (i == rows-1) {
                        System.out.println("buildUpHorizontalA");
                        return j;
                    }
                }
            }
        }
        return 999;
    }

    public static int buildUpHorizontalB(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == team) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        System.out.println("buildUpHorizontalB");
                        return j+1;
                    } else if (i == rows-1) {
                        System.out.println("buildUpHorizontalB");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int buildUpHorizontalC(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == team && circles[i][j+1].getTeam() == team &&
                        circles[i][j+2].getTeam() == 0 && circles[i][j+3].getTeam() == 0) {
                    if (i != rows-1 && circles[i+1][j+2].getTeam() != 0) {
                        System.out.println("buildUpHorizontalC");
                        return j+2;
                    } else if (i == rows-1) {
                        System.out.println("buildUpHorizontalC");
                        return j+2;
                    }
                }
            }
        }
        return 999;
    }

    public static int buildUpHorizontalD(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns-3; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == 0 &&
                        circles[i][j+2].getTeam() == team && circles[i][j+3].getTeam() == team) {
                    if (i != rows-1 && circles[i+1][j+1].getTeam() != 0) {
                        System.out.println("buildUpHorizontalD");
                        return j+1;
                    } else if (i == rows-1) {
                        System.out.println("buildUpHorizontalD");
                        return j+1;
                    }
                }
            }
        }
        return 999;
    }

    public static int buildUpVertical(Piece[][] circles, int rows, int columns, int team) {
        for (int i = 0; i < rows-2; i++) {
            for (int j = 0; j < columns; j++) {
                if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == team && circles[i+2][j].getTeam() == team) {
                    return j;
                }
            }
        }
        return 999;
    }

    public static int findBasicMove(Piece[][] circles, int rows, int columns, int team) {
        System.out.println("FindBasicMove");
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < columns-1; j++) {
                if (circles[i][j].getTeam() == team && circles[i + 1][j].getTeam() == team) {
                    return j;
                } else if (circles[i][j].getTeam() == team && circles[i][j+1].getTeam() == 0) {
                    return j+1;
                } else if (circles[i][j].getTeam() == 0 && circles[i][j+1].getTeam() == team) {
                    return j;
                } else if (circles[i][j].getTeam() == 0 && circles[i+1][j].getTeam() == team) {
                    return j;
                }
            }
        }
        return columns / 2;
    }

}
