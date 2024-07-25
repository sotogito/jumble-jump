package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.helper.PostfixToInfixConverter;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.util.DecimalPointFormatter;
import jumble_jump.util.Token;

import java.util.*;

/**
 * 곱하기 빈칸일때
 * 마지막 괄호는 확인하는 프로세스를 넣어ㅑ할거같은데
 */
//todo 곱하기 빈칸일때 괄호 바로 다음에
public class CalculatorService {

    private final Solving solving;
    private final Problem problem;
    private final InfixPostFixHelper infixPostFixHelper;

    private Stack<Token> resultStack = new Stack<>();
    private Double result;

    private List<Token> postfix;


    public CalculatorService(Problem problem, Solving solving, InfixPostFixHelper infixPostFixHelper) {
        this.problem = problem;
        this.solving = solving;
        this.infixPostFixHelper = infixPostFixHelper;
        this.postfix = infixPostFixHelper.convertToPostFix(problem);
    }

    public int getPostfixSize() {
        return this.postfix.size();
    }

    public boolean isSolveProblemOnce(int i){
        Token token = postfix.get(i);

        if (token instanceof NumberToken) {
            resultStack.push(token);
        }else if (token instanceof OperatorToken) {
            double num2 = ((NumberToken) resultStack.pop()).getNumber();
            double num1 = ((NumberToken) resultStack.pop()).getNumber();
            double result = ((OperatorToken) token).calculate(num1, num2);
            resultStack.push(new Number(result));
            solving.updateNumberOfSolving();

            return true;
        }
        return false;
    }

    public String getIntermediateStep(int i){
        return PostfixToInfixConverter.getIntermediateStep(resultStack,postfix,i+1);
    }

    public int getNumberOfSolving(){
        return this.solving.getNumberOfSolving();
    }

    public void setResult(){
        result = (((NumberToken)resultStack.pop()).getNumber());
    }

    public Double getResult(){
        return result;
    }

    public String getProblem(){
        return problem.getProblemText();
    }


}
