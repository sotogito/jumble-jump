package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.util.DecimalPointFormatter;
import jumble_jump.util.Token;

import java.lang.reflect.ParameterizedType;
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


    public CalculatorService(Problem problem, Solving solving, InfixPostFixHelper infixPostFixHelper) {
        this.problem = problem;
        this.solving = solving;
        this.infixPostFixHelper = infixPostFixHelper;
    }

    public void calculate(){
        List<Token> postfix = convertToPostfix();
        Deque<Double> operatorStack = new ArrayDeque<>();

        for (Token token : postfix) {
            if(token instanceof NumberToken) {
                System.out.print(((NumberToken) token).getNumber()+" ");
                operatorStack.push(((NumberToken) token).getNumber());
            }else if(token instanceof OperatorToken) {
                System.out.print(((OperatorToken) token).getOperatorType()+" ");
                double num2 = operatorStack.pop();
                double num1 = operatorStack.pop();
                double result = ((OperatorToken) token).calculate(num1,num2);
                operatorStack.push(result);
            }
        }
        System.out.println(DecimalPointFormatter.format(operatorStack.pop())+"정");
    }

    private List<Token> convertToPostfix() {
        return infixPostFixHelper.convertToPostFix(problem);
    }

}
