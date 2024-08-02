package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.repository.Solving;
import jumble_jump.domain.repository.SolvingRepository;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.converter.PostfixToInfixConverter;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.domain.token.Token;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculatorService {

    private final SolvingRepository solvingRepository;
    private  Problem problem;
    private final ProblemToPostFixConverter problemToPostFixConverter;

    private final Stack<Token> resultStack = new Stack<>();
    private Double result;

    private  List<Token> postfix;


    public CalculatorService(SolvingRepository solvingRepository, ProblemToPostFixConverter problemToPostFixConverter) {
        this.solvingRepository = solvingRepository;
        this.problemToPostFixConverter = problemToPostFixConverter;
    }

    public void setResult(){
        result = popNumberFromResultStack();
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        this.postfix = problemToPostFixConverter.convertToPostFix(problem);
        this.resultStack.clear(); // 상태 초기화
        this.result = null; // 결과 초기화
        solvingRepository.reset();
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

    public Double getResult(){
        return result;
    }

    public String getProblem(){
        return problem.getProblemText();
    }

    public int getPostfixSize() {
        return this.postfix.size();
    }

    public List<Solving> getSolvings() {
        return solvingRepository.getSolvings();
    }

    public int getTotalNumberOfSolvings() {
        return solvingRepository.getTotalNumberOfSolve();
    }

}
