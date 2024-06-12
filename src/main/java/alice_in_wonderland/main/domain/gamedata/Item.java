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

    public String getName() {
        return name;
    }

    public boolean getIsGrow() {
        return grow;
    }

    public static Item findGrowState(String input) {
        for (Item item : Item.values()) {
            if (input.equals(item.getName())) {
                return item;
            }
        }
        throw new IllegalArgumentException("없음");
    }


}
