package jumble_jump.domain.operators;

import jumble_jump.domain.component.OperatorType;

public interface Operator {
    OperatorType getOperatorType();
    double calculate(double number1, double number2);
}
