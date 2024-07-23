package jumble_jump.service.validator;

import jumble_jump.domain.token.OperatorToken;
import jumble_jump.util.Token;

import java.util.Deque;

public class OperatorPostFixValidator {

    public static boolean isValidOperator(Deque<Token> operatorStack,Token token) {
        if (operatorStack.isEmpty() || !(operatorStack.peek() instanceof OperatorToken topOperator)) {
            return true;
        } else if (((OperatorToken) token).getPriority() < topOperator.getPriority()) { //note * < - : pop안해도 됨 그냥 operator에 추가
            return true;
        }
        return false;
    }
}
