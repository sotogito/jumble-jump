package jumble_jump.domain.token.number;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.util.validator.NumberValidator;

public class Number implements NumberToken {
    private int number;

    public Number(int number) {
        NumberValidator.validate(number);
        this.number = number;
    }

    @Override
    public double getNumber() {
        return number;
    }

}
