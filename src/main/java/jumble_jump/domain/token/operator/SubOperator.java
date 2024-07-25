package jumble_jump.domain.token.operator;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.OperatorToken;

public class SubOperator implements OperatorToken {

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.SUBTRACT;
    }

    @Override
    public int getPriority() {
        return OperatorType.SUBTRACT.getPriority();
    }

    @Override
    public double calculate(double number1, double number2) {
        return number1 - number2;
    }

}
