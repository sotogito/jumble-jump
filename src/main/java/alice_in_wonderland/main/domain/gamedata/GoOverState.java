package alice_in_wonderland.main.domain.gamedata;

public enum GoOverState {
    GO_OVER("go over",true),
    STAY("stay",false);

    private final String name;
    private final boolean isOver;

    GoOverState(String name, boolean isOver) {
        this.name = name;
        this.isOver = isOver;
    }


}
