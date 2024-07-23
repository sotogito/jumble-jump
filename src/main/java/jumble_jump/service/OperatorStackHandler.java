package jumble_jump.service;

import jumble_jump.util.Token;

public interface OperatorStackHandler {
    void loopOperatorsUntilParenthesis();
    void loopPopRemainingOperators();
    void loopUpdateOperatorToken(Token token);
}
