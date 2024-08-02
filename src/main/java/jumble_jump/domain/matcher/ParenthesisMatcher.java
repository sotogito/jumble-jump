package jumble_jump.domain.matcher;

import jumble_jump.domain.token.parenthesis.Parenthesis;
import jumble_jump.domain.token.Token;
import org.springframework.stereotype.Component;

@Component
public class ParenthesisMatcher{

    public Token match(char input) {
        return new Parenthesis(input);
    }

}
