package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.converter.PostfixToInfixConverter;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.number.Number;
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
        return false;
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
