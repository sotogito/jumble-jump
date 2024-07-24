package jumble_jump.domain.token.number;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.util.validator.NumberValidator;

public class Number implements NumberToken {
    private double number;

    public Number(double number) {
        //NumberValidator.validate(number);
        this.number = number;
    }

    @Override
    public double getNumber() {
        return number;
    }

}
