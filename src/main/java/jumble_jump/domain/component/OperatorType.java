package jumble_jump.domain.component;

public enum OperatorType {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char symbol;

    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static OperatorType fromSymbol(char symbol) {
        for (OperatorType op : values()) {
            if (op.getSymbol() == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("사칙연사에 해당하지 않는 연산자가 있습니다.");
    }

    public static boolean isOperatorType(char symbol) {
        for (OperatorType op : values()) {
            if (op.getSymbol() == symbol) {
                return true;
            }
        }
        return false;
    }

}
