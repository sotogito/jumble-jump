package jumble_jump.service;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.type.ParenthesisType;
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


    private final List<Token> intermediateTokens;
    private Deque<Double> operators = new ArrayDeque<>();


    private double num1 = 0;
    private double num2 = 0;



    private List<Token> output = new ArrayList<>();
    private Deque<Token> operatorStack = new ArrayDeque<>();

    private  Set<Integer> openParenthesisPriorityList = new HashSet<>();
    private Deque<Token> parenthesisStack = new ArrayDeque<>();
    private ParenthesisToken beforeParenthesis;
    private int rightParenthesisCount = 0;
    private int leftParenthesisCount = 0;


    public CalculatorService(Problem problem, Solving solving, InfixPostFixHelper infixPostFixHelper) {
        this.problem = problem;
        this.intermediateTokens = problem.getProblemTokens();
        this.solving = solving;
        this.infixPostFixHelper = infixPostFixHelper;
    }

    public void calculate(){
        List<Token> postTokens = convertToPostfix();
        Deque<Double> problem = new ArrayDeque<>();


        for (Token token : postTokens) {
            if(token instanceof NumberToken) {
                System.out.print(((NumberToken) token).getNumber()+" ");
                problem.push(((NumberToken) token).getNumber());
            }else if(token instanceof OperatorToken) {
                System.out.print(((OperatorToken) token).getOperatorType()+" ");
                num2 = problem.pop();
                num1 = problem.pop();
                double result = ((OperatorToken) token).calculate(num1,num2);
                problem.push(result);
            }
        }
        System.out.println(problem.pop() +"정답");

    }

    private List<Token> convertToPostfix() {
        return infixPostFixHelper.convertToPostFix(problem);
    }


}
