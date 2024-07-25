package jumble_jump.domain.converter;

import jumble_jump.domain.Problem;
import jumble_jump.domain.manager.PostfixExpressionManager;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.validator.postfix.ParenthesisPostFixValidator;
import jumble_jump.util.Token;

import java.util.*;

/**
 * 불변으로 list넘기기
 * 중간 식 넘기기 - 괄호도 output에 추가?
 * 계산을 위한 list하고 조립을 위한 List를 따로
 */
public class ProblemToPostFixConverter implements InfixToPostFixConverter {
    private final PostfixExpressionManager postfixDataManager;
    private List<Token> outputForIntermediateStep = new ArrayList<>();

    private final Set<Integer> openParenthesisPriorityList = new HashSet<>();
    private Deque<Token> parenthesisStack = new ArrayDeque<>();
    private  ParenthesisToken beforeParenthesis;
    private int rightParenthesisCount = 0;
    private int leftParenthesisCount = 0;

    public ProblemToPostFixConverter(PostfixExpressionManager operatorStackHelper) {
        this.postfixDataManager = operatorStackHelper;
    }

    @Override
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
        ParenthesisPostFixValidator.validateNestedParentheses(openParenthesisPriorityList);
        ParenthesisPostFixValidator.validateNestedParenthesesEnd(rightParenthesisCount,leftParenthesisCount);

        postfixDataManager.loopPopRemainingOperators();
        return postfixDataManager.getOutput();
    }

    @Override
    public void updateOperatorToken(Token token) {
        postfixDataManager.loopUpdateOperatorToken(token);
    }

    @Override
    public void updateNumberToken(Token token){
        postfixDataManager.pushOutput(token);
    }


    @Override
    public void updateParenthesisToken(ParenthesisToken nowParenthesis) {
        beforeParenthesis = (ParenthesisToken) parenthesisStack.peek();

        if(beforeParenthesis == null){
            ParenthesisPostFixValidator.validateBeforeParenthesisNull(nowParenthesis);
            updateWhenOpenNowParenthesis(nowParenthesis);

        } else if (nowParenthesis.isOpenParenthesis()) {
            updateWhenOpenNowParenthesis(nowParenthesis);

        } else if (!nowParenthesis.isOpenParenthesis()) {
            if(beforeParenthesis.isOpenParenthesis()){
                ParenthesisPostFixValidator.validateWhenNowBeforeOpenState(nowParenthesis,beforeParenthesis,rightParenthesisCount);
                updateWhenParenthesisSectionClosed(nowParenthesis);
            }
            validateIsResetParenthesisData(rightParenthesisCount,leftParenthesisCount);
            postfixDataManager.loopOperatorsUntilParenthesis();
        }
    }

    private void updateWhenOpenNowParenthesis(ParenthesisToken nowParenthesis) {
        parenthesisStack.push(nowParenthesis);
        openParenthesisPriorityList.add(nowParenthesis.getParenthesisPriority());
        rightParenthesisCount++;
        postfixDataManager.pushParenthesisStack(nowParenthesis);
    }


    private void updateWhenParenthesisSectionClosed(ParenthesisToken nowParenthesis){
        postfixDataManager.pushOutput(nowParenthesis);

        parenthesisStack.removeFirst();
        leftParenthesisCount++;
    }

    private void validateIsResetParenthesisData(int rightParenthesisCount, int leftParenthesisCount){
        if(ParenthesisPostFixValidator.isResetParenthesesData(rightParenthesisCount,leftParenthesisCount)){
            resetParenthesisData();
        }
    }

    private void resetParenthesisData() {
        rightParenthesisCount = 0;
        leftParenthesisCount = 0;
        parenthesisStack = new ArrayDeque<>();
        beforeParenthesis = null;
    }



}
