package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.number.Number;
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

    private Double result;


    public CalculatorService(Problem problem, Solving solving, InfixPostFixHelper infixPostFixHelper) {
        this.problem = problem;
        this.solving = solving;
        this.infixPostFixHelper = infixPostFixHelper;
    }

    public void calculate(){
        List<Token> postfix = convertToPostfix();
        //Deque<Double> operatorStack = new ArrayDeque<>();//얘도 토큰
        Deque<Token> operatorStack = new ArrayDeque<>();//얘도 토큰

        for (int i = 0; i < postfix.size(); i++) {
            Token token = postfix.get(i);
            if (token instanceof NumberToken) {
                System.out.print(((NumberToken) token).getNumber() + " ");
                operatorStack.push(token);
                //todo interMediateStep에 추가
            }else if (token instanceof OperatorToken) {
                System.out.print(((OperatorToken) token).getOperatorType() + " ");
                double num2 = (((NumberToken) operatorStack.pop()).getNumber());
                double num1 = (((NumberToken) operatorStack.pop()).getNumber());
                double result = ((OperatorToken) token).calculate(num1, num2);

                operatorStack.push(new Number(result));


                convert(operatorStack,postfix,i);

            }
            //todo 중간 출력
            solving.updateNumberOfSolving();
        }

        result = (((NumberToken)operatorStack.pop()).getNumber());
        System.out.println(getFormattedResult()+"정");
    }

    public void convert(Deque<Token> operatorStack, List<Token> postfix,int startIndex){
        /**
         * 숫자만 추가하다가
         * 계산되면 계산식 추기
         */

        List<Token> result = new ArrayList<>(operatorStack);

        for(int i=startIndex+1; i<postfix.size(); i++){
            result.add(postfix.get(i));
        }
        System.out.println(result+"\n");

    }

    public java.lang.Number getFormattedResult(){
        return DecimalPointFormatter.format(result);
    }

    /*
    public String getIntermediateStep(){

    }

     */

    private List<Token> convertToPostfix() {
        return infixPostFixHelper.convertToPostFix(problem);
    }

}
