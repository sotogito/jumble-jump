package jumble_jump.domain.token.operator;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.OperatorToken;

public class MulOperator implements OperatorToken {

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MULTIPLY;
    }

    @Override
    public int getPriority() {
        return OperatorType.MULTIPLY.getPriority();
    }

    @Override
    public double calculate(double number1, double number2) {
        return number1 * number2;
    }
}
