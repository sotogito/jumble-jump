package jumble_jump.domain.manager;

import jumble_jump.domain.Resettable;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.Token;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostfixExpressionManager implements OperatorStackHandler , Resettable {

    private final Deque<Token> operatorStack = new ArrayDeque<>();
    private final List<Token> output = new ArrayList<>();

    public PostfixExpressionManager() {
    }

    public List<Token> getOutput() {
        return Collections.unmodifiableList(output);
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
            //operatorStack.pop(); //NOTE 원래 괄호는 넣는게 아님
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
                ((OperatorToken) operatorStack.peek()).getPriority() <= ((OperatorToken) token).getPriority()) {
            output.add(operatorStack.pop());
        }
        operatorStack.push(token);
    }

    public void reset(){
        operatorStack.clear();
        output.clear();
    }

}
