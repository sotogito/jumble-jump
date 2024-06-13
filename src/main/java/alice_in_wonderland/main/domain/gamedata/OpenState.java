package alice_in_wonderland.main.domain.gamedata;

public enum OpenState {
    OPEN("open",true),
    CLOSED("closed",false);

    private final String name;
    private final boolean isOpen;

    OpenState(String name, boolean open) {
        this.name = name;
        this.isOpen = open;
    }

    public boolean getOpenState() {
        return isOpen;
    }

    public String getName() {
        return name;
    }

    public static boolean findOpenState(String input){
        return input.equals(OPEN.name);
    }

}
