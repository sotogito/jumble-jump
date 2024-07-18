package jumble_jump.domain.token.operator;

import jumble_jump.domain.component.OperatorType;
import jumble_jump.domain.token.OperatorToken;

public class MulOperator implements OperatorToken {

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MULTIPLY;
    }

    @Override
    public double calculate(double number1, double number2) {
        return number1 * number2;
    }
}
