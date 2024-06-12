package alice_in_wonderland.main.domain.gamedata;

public enum OpenState {
    OPEN("open",true),
    CLOSED("closed",false);

    private final String name;
    private final boolean open;

    OpenState(String name, boolean open) {
        this.name = name;
        this.open = open;
    }
}
