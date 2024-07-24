package jumble_jump.util;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.helper.PostfixToInfixConverter;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.number.Number;

import java.util.*;

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
/*
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

        /*
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

         */
/*

        for (int i = 0; i < postfix.size(); i++) {
            Token token = postfix.get(i);
            if (token instanceof NumberToken) {
                resultStack.push(token);
            }else if (token instanceof OperatorToken) {
                double num2 = (((NumberToken) resultStack.pop()).getNumber());
                double num1 = (((NumberToken) resultStack.pop()).getNumber());
                double result = ((OperatorToken) token).calculate(num1, num2);
                resultStack.push(new Number(result));

                //todo 중간 식 출력
                solving.updateNumberOfSolving();
                System.out.println("\n"+ PostfixToInfixConverter.getIntermediateStep(resultStack,postfix,i+1)+"\n");
            }
        }
        result = (((NumberToken)resultStack.pop()).getNumber());
        System.out.println(getFormattedResult()+"정");
    }


    private void printIntermediateSteps(List<String> intermediateSteps) {
        for (String step : intermediateSteps) {
            System.out.println("Intermediate step: [" + step+"]");
        }
    }


    public java.lang.Number getFormattedResult(){
        return DecimalPointFormatter.format(result);
    }
    */

    /*
    public String getIntermediateStep(){

    }

     */
/*
    private List<Token> convertToPostfix() {
        return infixPostFixHelper.convertToPostFix(problem);
    }
    */







