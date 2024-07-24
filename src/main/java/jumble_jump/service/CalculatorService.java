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


    public CalculatorService(Problem problem, Solving solving, InfixPostFixHelper infixPostFixHelper) {
        this.problem = problem;
        this.solving = solving;
        this.infixPostFixHelper = infixPostFixHelper;
    }

    public void calculate(){
        List<Token> postfix = convertToPostfix();

        StringBuilder sb = new StringBuilder();
        for(Token token : postfix) {
            if(token instanceof NumberToken) {
                sb.append(((NumberToken) token).getNumber());
            }else if(token instanceof ParenthesisToken) {
                sb.append(((ParenthesisToken)token).getParenthesisType().getSymbol());
            }else if(token instanceof OperatorToken) {
                sb.append(((OperatorToken)token).getOperatorType().getSymbol());
            }
        }
        System.out.println("-----------");
        System.out.println(sb);
        System.out.println("-----------");


        for (int i = 0; i < postfix.size(); i++) {
            Token token = postfix.get(i);
            if (token instanceof NumberToken) {
                //System.out.print(((NumberToken) token).getNumber() + " ");
                resultStack.push(token);
                //todo interMediateStep에 추가
            }else if (token instanceof OperatorToken) {
                //System.out.print(((OperatorToken) token).getOperatorType() + " ");
                double num2 = (((NumberToken) resultStack.pop()).getNumber());
                double num1 = (((NumberToken) resultStack.pop()).getNumber());
                double result = ((OperatorToken) token).calculate(num1, num2);

                resultStack.push(new Number(result));


                System.out.println("\n"+PostfixToInfixConverter.getIntermediateStep(resultStack,postfix,i+1)+"\n");
            } else if (token instanceof ParenthesisToken) {
                System.out.println("괄호"+((ParenthesisToken)token).getParenthesisType().getSymbol());
                continue;
            }
            //todo 괄호면 skip
            solving.updateNumberOfSolving();
        }

        result = (((NumberToken)resultStack.pop()).getNumber());
        System.out.println(getFormattedResult()+"정");
    }

    public void convert(Deque<Token> operatorStack, List<Token> postfix, int startIndex) {
        //StringBuilder
        /**
         * 닫힌괄호 들어오면 열린괄호 만날때까지 식 num1,num2로 식 완성시키고 StringBuillder에 넣기
         * 괄호 섹션이 완성되면 넣기 앞에? SB를 스택에 넣음
         * 넣는 자료형은 Deque<String>이여야함 - 계산이 아니라 꺼내서 합치면 되기 때문에 상관 없음
         *
         * 만약에 같은 종류의 괄호가 나오면 괄호의 종류만 판단하고 처음 들어오는 괄호는 무조건 소 -> 중-> 대로 한다.
         *
         *
         *
         */
        List<String> intermediateSteps = new ArrayList<>();
        List<Token> result = new ArrayList<>(operatorStack);
        for (int i = startIndex + 1; i < postfix.size(); i++) {
            result.add(postfix.get(i));
        }

        Deque<String> stack = new ArrayDeque<>();
        for (Token token : result) {
            if (token instanceof NumberToken) {
                stack.push(String.valueOf(((NumberToken) token).getNumber()));
            } else if (token instanceof OperatorToken) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                String intermediateResult = "(" + num1 + " " + ((OperatorToken) token).getOperatorType().getSymbol() + " " + num2 + ")";
                stack.push(intermediateResult);
            }
        }
        intermediateSteps.add(stack.peek());
        printIntermediateSteps(intermediateSteps);
    }

    private void printIntermediateSteps(List<String> intermediateSteps) {
        for (String step : intermediateSteps) {
            System.out.println("Intermediate step: [" + step+"]");
        }
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
