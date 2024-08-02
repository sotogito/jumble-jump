package jumble_jump.domain.token;

import jumble_jump.domain.type.OperatorType;

//note Token 인터페이스에 Operator 상속
public interface OperatorToken extends Calculator {
    OperatorType getOperatorType();
    int getPriority();
    //double calculate(double number1, double number2);
}
