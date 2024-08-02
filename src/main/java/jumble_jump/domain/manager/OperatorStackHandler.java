package jumble_jump.domain.manager;

import jumble_jump.domain.token.Token;

public interface OperatorStackHandler {
    void loopOperatorsUntilParenthesis();
    void loopPopRemainingOperators();
    void loopUpdateOperatorToken(Token token);
}
