public class GameBuilder {

    public GuiBoard onePlayerModeEasy() {
        Player player1 = new Player(1,GuiColors.PIECE_YELLOW);
        AI ai = new AI(2,GuiColors.PIECE_BLUE,1);
        player1.setName();
        return new GuiBoard(player1,ai,1,ai.getDifficulty());
    }

    public GuiBoard onePlayerModeNormal() {
        Player player1 = new Player(1,GuiColors.PIECE_YELLOW);
        AI ai = new AI(2,GuiColors.PIECE_RED,2);
        player1.setName();
        return new GuiBoard(player1,ai,1,ai.getDifficulty());
    }

    public GuiBoard twoPlayerMode() {
        Player player1 = new Player(1,GuiColors.PIECE_YELLOW);
        Player player2 = new Player(2,GuiColors.PIECE_RED);
        player1.setName();
        player2.setName();
        return new GuiBoard(player1,player2,2,0);
    }

}
