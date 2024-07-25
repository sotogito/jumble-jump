package jumble_jump.domain.matcher;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.util.Token;
import jumble_jump.domain.token.number.Number;

public class NumberMatcher {

    public Token match(Double input) {
        return new Number(input);
    }

}
