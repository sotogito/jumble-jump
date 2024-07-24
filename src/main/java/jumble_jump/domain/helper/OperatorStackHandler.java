package jumble_jump.domain.helper;

import jumble_jump.util.Token;

public interface OperatorStackHandler {
    void loopOperatorsUntilParenthesis();
    void loopPopRemainingOperators();
    void loopUpdateOperatorToken(Token token);
}
