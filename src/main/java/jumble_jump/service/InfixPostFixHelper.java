package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.service.validator.OperatorPostFixValidator;
import jumble_jump.service.validator.ParenthesisValidator;
import jumble_jump.util.Token;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 불변으로 list넘기기
 * 중간 식 넘기기 - 괄호도 output에 추가?
 * 계산을 위한 list하고 조립을 위한 List를 따로
 */
public class InfixPostFixHelper {

    private List<Token> outputForIntermediateStep = new ArrayList<>();
    private List<Token> output = new ArrayList<>();
    private Deque<Token> operatorStack = new ArrayDeque<>();

    private final Set<Integer> openParenthesisPriorityList = new HashSet<>();
    private Deque<Token> parenthesisStack = new ArrayDeque<>();
    private  ParenthesisToken beforeParenthesis;
    private int rightParenthesisCount = 0;
    private int leftParenthesisCount = 0;



    public List<Token> convertToPostFix(Problem problem) {

        for(Token token : problem.getProblemTokens()){
            if(token instanceof NumberToken){
                updateNumberToken(token);
            } else if (token instanceof OperatorToken) {
                updateOperatorToken(token);
            } else if (token instanceof ParenthesisToken nowParenthesis) {
                updateParenthesisToken(nowParenthesis);
            }
        }


        ParenthesisValidator.validateNestedParentheses(openParenthesisPriorityList);
        ParenthesisValidator.validateNestedParenthesesEnd(rightParenthesisCount,leftParenthesisCount);

        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }

        return output;
    }




    private void updateParenthesisToken(ParenthesisToken nowParenthesis) {
        ParenthesisType nowParenthesisType = nowParenthesis.getParenthesisType();
        beforeParenthesis = (ParenthesisToken) parenthesisStack.peek();

        if(beforeParenthesis == null){
            ParenthesisValidator.validateBeforeParenthesisNull(nowParenthesis);

            parenthesisStack.push(nowParenthesis);
            openParenthesisPriorityList.add(nowParenthesis.getParenthesisPriority());
            rightParenthesisCount++;
            operatorStack.push(nowParenthesis);

        } else if (nowParenthesis.isOpenParenthesis()) {
            parenthesisStack.push(nowParenthesis);
            openParenthesisPriorityList.add(nowParenthesis.getParenthesisPriority());
            rightParenthesisCount++;
            operatorStack.push(nowParenthesis);

        } else if (!nowParenthesis.isOpenParenthesis()) {
            if(beforeParenthesis.isOpenParenthesis()){
                ParenthesisValidator.validateWhenNowBeforeOpenState(nowParenthesis,beforeParenthesis,rightParenthesisCount);

                parenthesisStack.removeFirst();
                leftParenthesisCount++;
            }

            if(ParenthesisValidator.isResetParenthesesData(rightParenthesisCount,leftParenthesisCount)){
                rightParenthesisCount = 0;
                leftParenthesisCount = 0;
                parenthesisStack = new ArrayDeque<>();
                beforeParenthesis = null;
            }

            while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis())) {
                output.add(operatorStack.pop());
            }
            if (!operatorStack.isEmpty() && operatorStack.peek() instanceof ParenthesisToken && ((ParenthesisToken) operatorStack.peek()).isOpenParenthesis()) {
                operatorStack.pop(); // Remove the opening parenthesis
            }

        }

    }



    private void updateOperatorToken(Token token) {
        while (!OperatorPostFixValidator.isValidOperator(operatorStack, token)) {
            output.add(operatorStack.pop());
        }
        operatorStack.push(token);
    }


    private void updateNumberToken(Token token){
        output.add(token);
    }

}
