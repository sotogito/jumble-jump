package jumble_jump.domain.helper;

import jumble_jump.domain.token.OperatorToken;
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

    public void pushOutput(Token token) {
        output.add(token);
    }

    public void loopOperatorsUntilParenthesis() {
        while (!operatorStack.isEmpty() &&
                !(operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis())) {
            output.add(operatorStack.pop()); //여는 괄호가 나오는 동안 해야지
        }
        if (!operatorStack.isEmpty() &&
                operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis()) {
            //operatorStack.pop(); //fixme
            output.add(operatorStack.pop());
        }
    }

    public void loopPopRemainingOperators(){
        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }
    }

    public void loopUpdateOperatorToken(Token token){
        while (!operatorStack.isEmpty() && operatorStack.peek() instanceof OperatorToken &&
                ((OperatorToken) operatorStack.peek()).getPriority() < ((OperatorToken) token).getPriority()) {
            output.add(operatorStack.pop());
        }
        operatorStack.push(token);
    }

    public  boolean isValidOperator(Deque<Token> operatorStack,Token token) {
        if (operatorStack.isEmpty() || !(operatorStack.peek() instanceof OperatorToken topOperator)) {
            return true;
        } else if (((OperatorToken) token).getPriority() < topOperator.getPriority()) { //note * < - : pop안해도 됨 그냥 operator에 추가
            return true;
        }
        return false;
    }

}
