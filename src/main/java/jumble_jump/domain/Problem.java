package jumble_jump.domain;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;
import jumble_jump.util.converter.ProblemToStringConverter;

import java.util.ArrayList;
import java.util.List;

//todo 정적?
public class Problem {
    private final List<Token> problemTokens;

    public Problem(List<Token> tokens) {
        this.problemTokens = tokens;
    }

    public List<Token> getProblemTokens() {
        return problemTokens;
    }

    public String getProblemText() {
        return ProblemToStringConverter.getProblemText(problemTokens);
    }
}
