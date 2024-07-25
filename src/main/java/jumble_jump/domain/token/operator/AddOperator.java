package jumble_jump.domain.token.operator;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.OperatorToken;

//note 연산자는 행동이 저장되어야함
public class AddOperator implements OperatorToken {

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.ADD;
    }

    @Override
    public int getPriority() {
        return OperatorType.ADD.getPriority();
    }

    @Override
    public double calculate(double number1, double number2) {
        return number1 + number2;
    }

}
