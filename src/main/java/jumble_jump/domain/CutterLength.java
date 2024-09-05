package jumble_jump.domain;

public enum CutterLength {
    SAME(0),
    LONG(-1),
    SHORT(1);

    private int calculatedValue;

    CutterLength(int calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public int getValue() {
        return calculatedValue;
    }

}
