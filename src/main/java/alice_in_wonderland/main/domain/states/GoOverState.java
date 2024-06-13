package alice_in_wonderland.main.domain.states;

public enum GoOverState {
    GO_OVER("go over", true),
    STAY("stay", false);

    private final String name;
    private final boolean isOver;

    GoOverState(String name, boolean isOver) {
        this.name = name;
        this.isOver = isOver;
    }

    public boolean getIsOver() {
        return isOver;
    }

    public String getName() {
        return name;
    }

    public static boolean findGoOverState(String input) {
        return input.equals(GO_OVER.name);
    }

}