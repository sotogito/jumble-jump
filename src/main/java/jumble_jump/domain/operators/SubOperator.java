package jumble_jump.domain.operators;

import jumble_jump.domain.component.OperatorType;

public class SubOperator implements Operator {


    @Override
    public OperatorType getOperatorType() {
        return OperatorType.SUBTRACT;
    }

    @Override
    public double calculate(double number1, double number2) {
        return number1 - number2;
    }
}
