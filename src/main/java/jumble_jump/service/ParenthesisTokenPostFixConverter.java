package jumble_jump.service;

import jumble_jump.domain.token.ParenthesisToken;

public interface ParenthesisTokenPostFixConverter extends InfixPostFixConverter{
    void updateParenthesisToken(ParenthesisToken nowParenthesis);

    void updateWhenOpenNowParenthesis(ParenthesisToken nowParenthesis);
    void updateWhenCloseNowParenthesis(ParenthesisToken nowParenthesis);

    void loopOperatorsUntilParenthesis();
}
