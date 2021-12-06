import javax.swing.*;
import java.awt.*;

public class Piece extends JLabel {
    private int xPos;
    private int yPos;
    private int team; // 0: no team, 1: player 1, 2: player 2;

    public Piece(){
        this.xPos = xPos;
        this.yPos = yPos;
        this.team = 0;

    }

    public int getTeam() {
        return team;
    }

    public void changeTeam(int teamTo){
        if(team==0){
            this.team = teamTo;
            if (team==1){
                setBackground(Color.YELLOW);
            }
            if (team==2){
                setBackground(Color.red);
            }
        }

    }
}
