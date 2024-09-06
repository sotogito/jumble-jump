package jumble_jump.domain;

public enum CutterLength {
    SAME(0, "nothing"),
    LONG(-1, "end"),
    SHORT(1, "start");

    private final int calculatedValue;
    private final String adjustingPoint;

    CutterLength(int calculatedValue, String adjustingPoints) {
        this.calculatedValue = calculatedValue;
        this.adjustingPoint = adjustingPoints;
    }

    public int getValue() {
        return calculatedValue;
    }

    public String getAdjustingPoint() {
        return adjustingPoint;
    }

}
