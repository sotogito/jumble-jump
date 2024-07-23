package jumble_jump.service;

import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;

import java.util.Deque;
import java.util.List;

public class OperatorStackHelper {

    private Deque<Token> operatorStack;

    public OperatorStackHelper() {
    }

    public void setOperatorStack(Deque<Token> operatorStack) {
        this.operatorStack = operatorStack;
    }

    public void loopOperatorsUntilParenthesis(List<Token> output) {
        while (!operatorStack.isEmpty() &&
                !(operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis())) {
            output.add(operatorStack.pop());
        }
        if (!operatorStack.isEmpty() &&
                operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis()) {
            operatorStack.pop();
        }
    }

}
