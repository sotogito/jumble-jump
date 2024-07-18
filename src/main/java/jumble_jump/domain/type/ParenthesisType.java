package jumble_jump.domain.type;

public enum ParenthesisType {
    PARENTHESIS_LEFT('(', 1),
    PARENTHESIS_RIGHT(')', 1),
    CURLY_BRACE_LEFT('{', 2),
    CURLY_BRACE_RIGHT('}', 2),
    BRACKET_LEFT('[', 3),
    BRACKET_RIGHT(']', 3),
    ;

    private final char symbol;
    private final int priority;

    ParenthesisType(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }

    public static ParenthesisType fromSymbol(char symbol) {
        for (ParenthesisType p : values()) {
            if (p.getSymbol() == symbol) {
                return p;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 괄호입니다.");
    }

    public static boolean isParenthesisType(char input) {
        for (ParenthesisType p : values()) {
            if (p.getSymbol() == input) {
                return true;
            }
        }
        return false;
    }

    public ParenthesisType getAdvancedPriority(ParenthesisType p1, ParenthesisType p2){
        if (p1.getPriority() > p2.getPriority()) {
            return p1;
        }
        return p2;
    }



}
