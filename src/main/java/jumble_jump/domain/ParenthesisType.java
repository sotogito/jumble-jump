package jumble_jump.domain;

public enum ParenthesisType {
    PARENTHESIS_LEFT('('),
    PARENTHESIS_RIGHT(')'),
    CURLY_BRACE_LEFT('{'),
    CURLY_BRACE_RIGHT('}'),
    BRACKET_LEFT('['),
    BRACKET_RIGHT(']'),
    ;

    private final char symbol;

    ParenthesisType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static ParenthesisType fromSymbol(char symbol) {
        for (ParenthesisType p : values()) {
            if (p.getSymbol() == symbol) {
                return p;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 괄호입니다.");
    }

}
