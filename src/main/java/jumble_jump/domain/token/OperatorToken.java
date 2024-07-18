package jumble_jump.domain.token;

import jumble_jump.domain.component.OperatorType;
import jumble_jump.util.Token;
//note Token 인터페이스에 Operator 상속
public interface OperatorToken extends Token {
    /**
     * Map으로 담아주기보다 고유한 특성이 있기 때문에 안에서 Enum 상수로 정의하는게 맞다
     */
    OperatorType getOperatorType();
    double calculate(double number1, double number2);
}
