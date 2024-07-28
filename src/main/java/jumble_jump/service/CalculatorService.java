package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.converter.PostfixToInfixConverter;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.util.Token;

import java.util.*;

public class CalculatorService {

    private final Solving solving;
    private final Problem problem;
    private final ProblemToPostFixConverter problemToPostFixConverter;

    private final Stack<Token> resultStack = new Stack<>();
    private Double result;

    private final List<Token> postfix;

    public CalculatorService(Problem problem, Solving solving, ProblemToPostFixConverter problemToPostFixConverter) {
        this.problem = problem;
        this.solving = solving;
        this.problemToPostFixConverter = problemToPostFixConverter;
        this.postfix = problemToPostFixConverter.convertToPostFix(problem);
    }

    public void setResult(){
        result = popNumberFromResultStack();
    }

    public boolean isSolveProblemOnce(int i){
        Token token = postfix.get(i);

        if (token instanceof NumberToken) {
            resultStack.push(token);
        }else if (token instanceof OperatorToken) {
            double num2 = popNumberFromResultStack();
            double num1 = popNumberFromResultStack();
            double result = ((OperatorToken) token).calculate(num1, num2);
            resultStack.push(new Number(result));
            solving.updateNumberOfSolving();

            return true;
        }
        /**
         * 우선순위가 가장 낮은 괄호부터 찾아서 푼다
         * 우선순위가 가장 남을 괄호가 없으면 그다으으로 넘어간다.
         * while(그당시우선순위괄호가존재할때까지)
         *
         * 중괄호인데앞에 대괄호가 있으면
         * 대고라호 앞에 연산자수 +1만큼의 닫힌 괄호 앞에 개수를 구해서 i-그 수 앞의 수와
         * 연산자를 차례대로 곱한다.
         *
         */
        return false;
    }

    private void test(int i){
        Token token = postfix.get(i);
        int beforeParenthesis = ParenthesisType.PARENTHESIS_OPEN.getPriority();
        ParenthesisType nowParenthesis = ParenthesisType.PARENTHESIS_OPEN;


        if (token instanceof NumberToken) {
            resultStack.push(token);
        }else if (token instanceof OperatorToken) {
            double num2 = popNumberFromResultStack();
            double num1 = popNumberFromResultStack();
            double result = ((OperatorToken) token).calculate(num1, num2);
            resultStack.push(new Number(result));
            solving.updateNumberOfSolving();

        }

        while (!postfix.contains(nowParenthesis.getSymbol())){
            if (token instanceof NumberToken) {
                resultStack.push(token);
            } else if (token instanceof ParenthesisToken parenthesisToken) {
                nowParenthesis = parenthesisToken.getParenthesisType();

                int count = 0;
                for(int j = i-1; j > 0 ; j--){
                    //만약 자기보다 우선순위가 높은 괄호가 보이면
                    if(!(postfix.get(j) instanceof OperatorToken)){//열린괄호의 바오 앞에 있는 연산자만
                        break;
                    }
                    count++;

                    //i-count+1+2+그괄호의 연산자수만큼의 인덱스로 돌아감
                    //for 해당 열린 괄호가 나올떄까지 숫자 다 연산해


                }


            }
        }
    }

    private Double popNumberFromResultStack(){
        try{
            return ((NumberToken) resultStack.pop()).getNumber();
        }catch (Exception e){
            throw new IllegalArgumentException("잘못된 식입니다 - CS");
        }
    }

    public String getIntermediateStep(int i){
        return PostfixToInfixConverter.getIntermediateStep(resultStack,postfix,i+1);
    }

    public int getNumberOfSolving(){
        return this.solving.getNumberOfSolving();
    }

    public Double getResult(){
        return result;
    }

    public String getProblem(){
        return problem.getProblemText();
    }

    public int getPostfixSize() {
        return this.postfix.size();
    }

}
