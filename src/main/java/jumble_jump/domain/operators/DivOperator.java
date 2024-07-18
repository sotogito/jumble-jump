package jumble_jump.domain.operators;

import jumble_jump.domain.component.OperatorType;

public class DivOperator implements Operator {
    @Override
    public OperatorType getOperatorType() {
        return OperatorType.DIVIDE;
    }

    @Override
    public double calculate(double number1, double number2) {
        return number1 / number2;
    }
}
