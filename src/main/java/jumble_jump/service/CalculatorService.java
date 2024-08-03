package jumble_jump.service;

import jumble_jump.domain.Resettable;
import jumble_jump.domain.repository.Problem;
import jumble_jump.domain.repository.Solving;
import jumble_jump.domain.repository.SolvingRepository;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.converter.PostfixToInfixConverter;
import jumble_jump.domain.repository.SolvingRepositoryImpl;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.domain.token.Token;
import jumble_jump.util.DecimalPointFormatter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculatorService implements Resettable {

    private final SolvingRepository solvingRepository;
    private  Problem problem;
    private final ProblemToPostFixConverter problemToPostFixConverter;

    private final Stack<Token> resultStack = new Stack<>();
    private List<Token> postfix;


    public CalculatorService(SolvingRepository solvingRepository, ProblemToPostFixConverter problemToPostFixConverter) {
        this.solvingRepository = solvingRepository;
        this.problemToPostFixConverter = problemToPostFixConverter;
    }

    public void setProblem(Problem problem) {
        reset();
        this.problem = problem;
        this.postfix = problemToPostFixConverter.convertToPostFix(problem);
    }

    public void calculate(){
        for(int i = 0; i < getPostfixSize(); i++){
            Token token = postfix.get(i);

            if (token instanceof NumberToken) {
                resultStack.push(token);
            }else if (token instanceof OperatorToken) {
                double num2 = popNumberFromResultStack();
                double num1 = popNumberFromResultStack();
                double result = ((OperatorToken) token).calculate(num1, num2);
                resultStack.push(new Number(result));
                solvingRepository.saveSolving(getIntermediateStep(i));
            }
        }
        setResult();
    }

    public void setResult(){
        solvingRepository.setResult(popNumberFromResultStack());
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

    public int getPostfixSize() {
        return this.postfix.size();
    }

    public void reset(){
        this.problemToPostFixConverter.reset();
        this.resultStack.clear();
        this.solvingRepository.reset();
    }


    //note 출력을 위한
    public String getProblemText(){
        return problem.getProblemText();
    }

    public List<Solving> getSolvingList() {
        return solvingRepository.getSolving();
    }

    public java.lang.Number getResult() {
        return solvingRepository.getResult();
    }

    public int getTotalNumberOfSolving(){
        return solvingRepository.getTotalNumberOfSolve();
    }

    public String getProblemPostFix(){
        List<String> result = new ArrayList<>();

        for(Token token : postfix){
            if(token instanceof NumberToken){
                java.lang.Number num = DecimalPointFormatter.problemFormat(((NumberToken) token).getNumber());
                result.add(String.valueOf(num));
            }else if (token instanceof OperatorToken){
                result.add(String.valueOf(((OperatorToken)token).getOperatorType().getSymbol()));
            }else if (token instanceof ParenthesisToken){
                result.add(((ParenthesisToken)token).getParenthesisText());
            }
        }
        return String.join(" , ", result);
    }


}
