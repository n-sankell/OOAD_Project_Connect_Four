public class GameAI extends Player {

    private String name;

    public GameAI(int team) {
        super(team);
    }

    public void setName() {
        name = "AI";
    }

    public String getName() {
        return name;
    }

}
