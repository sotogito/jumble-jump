package jumble_jump.domain;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;
import jumble_jump.util.converter.ProblemToStringConverter;

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
        return ProblemToStringConverter.getProblemText(problemTokens);
    }
}
