package jumble_jump.domain.converter;

import jumble_jump.domain.repository.Problem;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.Token;

import java.util.List;

public interface InfixToPostFixConverter {
    List<Token> convertToPostFix(Problem problem);

    void updateNumberToken(Token token);
    void updateOperatorToken(Token token);
    void updateParenthesisToken(ParenthesisToken nowParenthesis);

}
