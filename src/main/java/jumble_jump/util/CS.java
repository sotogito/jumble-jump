package jumble_jump.util;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.number.Number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
 /*
public class CS {

    package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.util.DecimalPointFormatter;
import jumble_jump.util.Token;

import java.util.*;

    /**
     * 곱하기 빈칸일때
     * 마지막 괄호는 확인하는 프로세스를 넣어ㅑ할거같은데
     */
//todo 곱하기 빈칸일때 괄호 바로 다음에
    /*
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

        public void convert(Deque<Token> operatorStack, List<Token> postfix, int startIndex) {
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



        private List<Token> convertToPostfix() {
            return infixPostFixHelper.convertToPostFix(problem);
        }

    }



}
*/
