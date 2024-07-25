package jumble_jump.domain.type;

import jumble_jump.domain.token.ParenthesisToken;

public enum ParenthesisType {
    PARENTHESIS_OPEN('(', 1), PARENTHESIS_CLOSE(')', 1),
    CURLY_BRACE_OPEN('{', 2), CURLY_BRACE_CLOSE('}', 2),
    BRACKET_OPEN('[', 3), BRACKET_CLOSE(']', 3),
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
        throw new IllegalArgumentException("존재하지 않는 심븛입니다.");
    }

    public static boolean isParenthesisType(char input) {
        for (ParenthesisType p : values()) {
            if (p.getSymbol() == input) {
                return true;
            }
        }
        return false;
    }

    public static ParenthesisType findCloseTypeByOpen(ParenthesisToken open) {
        for (ParenthesisType p : values()) {
            if(open.getParenthesisPriority() == p.getPriority() && !p.isOpen()) {
                return p;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 괄호입니다.");
    }


    public boolean isOpen() {
        return this == PARENTHESIS_OPEN || this == CURLY_BRACE_OPEN || this == BRACKET_OPEN;
    }



    public static boolean isNextParenthesis(ParenthesisType before, ParenthesisType now){
        int subPriority = now.getPriority() - before.getPriority();
        return subPriority == 0 || subPriority == 1;
    }

    public static boolean isNextOpen(ParenthesisType before, ParenthesisType now){
        int subPriority = now.getPriority() - before.getPriority();
        return subPriority == 0 || subPriority == -1;
    }

    public static boolean isNextClose(ParenthesisType before, ParenthesisType now){
        int subPriority = now.getPriority() - before.getPriority();
        return subPriority == 0 || subPriority == 1;
    }

    public static boolean isSamePriority(ParenthesisType before, ParenthesisType now){
        return before.getPriority() == now.getPriority();
    }


    public static boolean isSameOpenState(ParenthesisType before, ParenthesisType now){
        return (before.isOpen() && now.isOpen()) || (!before.isOpen() && !now.isOpen());
    }





}
