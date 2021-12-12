public class Player {
    private String name;
    private final int team;
    private int score = 0;
    LoginJop lj = new LoginJop();

    public Player(int team) {
        this.team = team;
    }

    public void setName() {
        while (name == null || name.trim().equals("")) {
        name = lj.optionPane();
        }
        name = name.trim();
    }

    public String getName() {
        return name.toUpperCase();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getTeam() {
        return team;
    }
}
