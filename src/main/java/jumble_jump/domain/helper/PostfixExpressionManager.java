package jumble_jump.domain.helper;

import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.validator.postfix.OperatorPostFixValidator;
import jumble_jump.util.Token;

import java.util.*;

public class PostfixExpressionManager implements OperatorStackHandler {

    private final Deque<Token> operatorStack = new ArrayDeque<>();
    private final List<Token> output = new ArrayList<>();

    public PostfixExpressionManager() {
    }

    public List<Token> getOutput() {
        return Collections.unmodifiableList(output);
    }

    public Deque<Token> getOperatorStack() {
        return  operatorStack;
    }

    public void pushParenthesisStack(Token token) {
        operatorStack.push(token);
    }

    public void pushNumberOutput(Token token) {
        output.add(token);
    }

    public void loopOperatorsUntilParenthesis() {
        while (!operatorStack.isEmpty() &&
                !(operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis())) {
            output.add(operatorStack.pop());
        }
        if (!operatorStack.isEmpty() &&
                operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis()) {
            operatorStack.pop();
        }
    }

    public void loopPopRemainingOperators(){
        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }
    }

    public void loopUpdateOperatorToken(Token token){
        while (!OperatorPostFixValidator.isValidOperator(operatorStack, token)) {
            output.add(operatorStack.pop());
        }
        operatorStack.push(token);
    }

}
