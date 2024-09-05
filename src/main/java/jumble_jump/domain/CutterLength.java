package jumble_jump.domain;

public enum CutterLength {
    SAME(0,"nothing"),
    LONG(1,"start"),
    SHORT(-1,"end");

    private int calculatedValue;
    private String adjustingPoint;

    CutterLength(int calculatedValue, String adjustingPoints) {
        this.calculatedValue = calculatedValue;
        this.adjustingPoint = adjustingPoints;
    }

    public int getValue() {
        return calculatedValue;
    }

}
