package jumble_jump.domain.type;

public enum MapType {
    LAND(0, "육지"),
    SEA(1, "바다");

    private final int value;
    private final String description;

    // Enum constructor
    MapType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    // Optional: Adding a method to get MapType by value
    public static MapType fromValue(int value) {
        for (MapType type : MapType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid map type value: " + value+"\n");
    }
}
