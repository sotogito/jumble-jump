package jumble_jump.domain.manager;

import jumble_jump.util.Token;

public interface OperatorStackHandler {
    void loopOperatorsUntilParenthesis();
    void loopPopRemainingOperators();
    void loopUpdateOperatorToken(Token token);
}
