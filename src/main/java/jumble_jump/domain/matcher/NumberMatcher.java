package jumble_jump.domain.matcher;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.util.Token;
import jumble_jump.domain.token.number.Number;

public class NumberMatcher implements Matcher{
    @Override
    public Token match(char input) {
        return new Number(validateNumber(input));
    }

    private double validateNumber(char input) {
        try {
            return Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 입력하세요");
        }
    }
}
