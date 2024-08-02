package jumble_jump.domain.matcher;

import jumble_jump.domain.token.Token;
import jumble_jump.domain.token.number.Number;
import org.springframework.stereotype.Component;

@Component
public class NumberMatcher {

    public Token match(Double input) {
        return new Number(input);
    }

}
