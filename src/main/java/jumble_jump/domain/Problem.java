package jumble_jump.domain;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private static final List<Token> problemTokens = new ArrayList<>();

    public static void setProblemTokens(List<Token> tokens) {
        problemTokens.addAll(tokens);
    }

    public static List<Token> getProblemTokens() {
        return problemTokens;
    }

    public static String getProblemText() {
        List<String> result = new ArrayList<>();
        for (Token token : problemTokens) {
            if(token instanceof NumberToken) {
                int number = ((NumberToken) token).getNumber();
                result.add(String.valueOf(number));
            }else if(token instanceof OperatorToken) {
                OperatorType operatorType = ((OperatorToken) token).getOperatorType();
                result.add(Character.toString(operatorType.getSymbol()));
            }else if(token instanceof ParenthesisToken) {
                result.add(((ParenthesisToken) token).getParenthesisText());
            }
        }

        return String.join(" ", result);
    }
}
