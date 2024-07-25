package jumble_jump.domain.type;

public enum OperatorType {
    ADD('+',2),
    SUBTRACT('-',2),
    MULTIPLY('*',1),
    DIVIDE('/',1);

    private final char symbol;
    private final int priority;

    OperatorType(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }

    public static OperatorType fromSymbol(char symbol) {
        for (OperatorType op : values()) {
            if (op.getSymbol() == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("사칙연산에 해당 연산 기능은 제공하지 않습니다.");
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
