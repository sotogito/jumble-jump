package jumble_jump.domain;

import jumble_jump.view.NumberValidator;

public class Number {
    private int number;

    public Number(int number) {
        NumberValidator.validate(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
