public class Game {
    Player player1;Player player2;
    public GuiBoard onePlayerMode(){
        this.player1=new Player(1);
        this.player2=new Player(2);
        return new GuiBoard(player1,player2);

    }
    public GuiBoard twoPlayerMode(){
        this.player1=new Player(1);
        this.player2=new Player(2);
        return new GuiBoard(player1,player2);
    }


    //new GuiFrame(player1, player2);

}
