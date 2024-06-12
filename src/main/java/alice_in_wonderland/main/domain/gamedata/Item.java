package alice_in_wonderland.main.domain.gamedata;

public enum Item {
    DRINK("음료",true),
    MUSHROOM("버섯",false);

    private final String name;
    private final boolean grow;

    Item(String name, boolean grow) {
        this.name = name;
        this.grow = grow;
    }
}
