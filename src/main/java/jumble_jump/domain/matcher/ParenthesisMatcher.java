package jumble_jump.domain.matcher;

import jumble_jump.domain.component.ParenthesisType;
import jumble_jump.domain.token.parenthesis.Parenthesis;
import jumble_jump.util.Token;

public class ParenthesisMatcher implements Matcher{

    @Override
    public Token match(char input) {
        return new Parenthesis(input);
    }

}
