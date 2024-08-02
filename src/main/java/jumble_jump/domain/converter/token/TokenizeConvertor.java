package jumble_jump.domain.converter.token;

import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.Token;

import java.util.List;

public interface TokenizeConvertor {
    List<Token> tokenize(String inputProblem);
    void problemToChars(String problemString);
    List<Token> getTokenizedResult(String inputProblem);

    int updateNumber(int index);
    int updateOperator(char c,int index);
    int updateParenthesis(ParenthesisToken nowParenthesisToken, int index);
}
