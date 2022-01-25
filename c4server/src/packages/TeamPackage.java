package packages;

import java.io.Serializable;

public class TeamPackage implements Serializable {

    private int team;

    public TeamPackage(int team) {
        this.team = team;
    }

    public int getTeam() {
        return team;
    }
}
