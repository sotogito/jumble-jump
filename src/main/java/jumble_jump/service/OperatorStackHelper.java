package jumble_jump.service;

import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.service.validator.OperatorPostFixValidator;
import jumble_jump.util.Token;

import java.util.Deque;
import java.util.List;

public class OperatorStackHelper implements OperatorStackHandler{

    private Deque<Token> operatorStack;
    private List<Token> output;

    public OperatorStackHelper() {
    }

    public void setOperatorStack(Deque<Token> operatorStack) {
        this.operatorStack = operatorStack;
    }

    public void setOutput(List<Token> output) {
        this.output = output;
    }

    //note InfixPostFixHelper의 output이 업데이트됨
    //note static으로 해서 operatorStack넘겨서 업데이트 해도 됨
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
