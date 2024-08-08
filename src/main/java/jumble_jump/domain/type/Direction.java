package jumble_jump.domain.type;

public enum Direction {
    NORTH(0, "북"),
    EAST(1, "동"),
    SOUTH(2, "남"),
    WEST(3, "서");

    private final int number;
    private final String korean;

    // Enum constructor
    Direction(int number, String korean) {
        this.number = number;
        this.korean = korean;
    }

    public int getNumber() {
        return number;
    }

    public String getKorean() {
        return korean;
    }

    public static Direction getLeftTurnDirection(Direction nowDirection){
        if(nowDirection == NORTH){
            return WEST;
        }
        return findDirectionByNextNumber(nowDirection);
    }

    public static Direction findDirectionByNextNumber(Direction nowDirection){
        int nextNumber = nowDirection.number -1;
        for(Direction direction : Direction.values()){
            if(direction.number == nextNumber){
                return direction;
            }
        }
        throw new RuntimeException("Direction number not found\n");
    }

    // Optional: Adding a method to get direction by number
    public static Direction fromNumber(int number) {
        for (Direction dir : Direction.values()) {
            if (dir.getNumber() == number) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Invalid direction number: " + number+"\n");
    }
}
