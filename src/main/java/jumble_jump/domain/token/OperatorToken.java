package jumble_jump.domain.token;

import jumble_jump.domain.Calculator;
import jumble_jump.domain.type.OperatorType;
import jumble_jump.util.Token;
//note Token 인터페이스에 Operator 상속
public interface OperatorToken extends Calculator {
    OperatorType getOperatorType();
    int getPriority();
    //double calculate(double number1, double number2);
}
