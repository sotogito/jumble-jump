package jumble_jump.domain;

import jumble_jump.domain.token.Token;
import jumble_jump.util.converter.ProblemToStringConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
