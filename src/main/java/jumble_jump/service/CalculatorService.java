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




    /*


    private List<Token> convertToPostfix() {



        for (Token token : problem.getProblemTokens()) {
            if (token instanceof NumberToken) {
                output.add(token);
            }

            else if (token instanceof OperatorToken) {

                while (true) {
                    if (operatorStack.isEmpty() || !(operatorStack.peek() instanceof OperatorToken topOperator)) {
                        break;
                    }
                    if (((OperatorToken) token).getPriority() < topOperator.getPriority()) {
                        break;
                    }
                    output.add(operatorStack.pop());
                }

                operatorStack.push(token);



            } else if (token instanceof ParenthesisToken nowParenthesis) {
                ParenthesisType nowParenthesisType = nowParenthesis.getParenthesisType();
                beforeParenthesis = (ParenthesisToken) parenthesisStack.peek();

                //
                System.out.println("----------------");
                System.out.println(nowParenthesisType.getSymbol() +"현재");
                if(beforeParenthesis == null){
                    System.out.println("null" +"이전");
                }else {
                    System.out.println(beforeParenthesis.getParenthesisType().getSymbol() +"이전");
                }
                //


                if(beforeParenthesis == null){ //첫 시작
                    System.out.println("첫괄호");
                    if(!nowParenthesis.isOpenParenthesis()){
                        throw new IllegalArgumentException("첫 괄호는 열림 괄호여야 합니다.");
                    }
                    parenthesisStack.push(nowParenthesis);
                    openParenthesisPriorityList.add(nowParenthesis.getParenthesisPriority());
                    rightParenthesisCount++;
                    operatorStack.push(token);
                    continue;
                }

                //note 열린 괄호 ({[
                if(nowParenthesis.isOpenParenthesis()){
                    System.out.println("열린 -> 열린");
                    parenthesisStack.push(nowParenthesis);
                    openParenthesisPriorityList.add(nowParenthesis.getParenthesisPriority());
                    rightParenthesisCount++;
                    operatorStack.push(token);
                }


                //note 닫힌 괄호 )}]
                if(!nowParenthesis.isOpenParenthesis()){
                    if(beforeParenthesis.isOpenParenthesis()){
                        System.out.println("열린 -> 닫힌");
                        if(!ParenthesisType.isSamePriority(beforeParenthesis.getParenthesisType(), nowParenthesisType)){
                            throw new IllegalArgumentException("열리고, 닫힌 괄호의 종류가 달라요"); //fixme
                        } else if (rightParenthesisCount <= 1) {
                            if(nowParenthesis.getParenthesisPriority() != 1){
                                throw new IllegalArgumentException("단독으로 사용하는 괄호는 소괄호를 사용하세요.");
                            }
                        }
                    }

                    parenthesisStack.removeFirst();
                    leftParenthesisCount++;


                    if(rightParenthesisCount == leftParenthesisCount){ //note 중첩이 끝났는지 확인
                        System.out.println("초기화");
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



        }

        if (openParenthesisPriorityList.size() >= 2) { //note parenthesisStack에 들어있는 열린 괄호들을 우선순위로 정렬한다음 그 값이 isnectOpen인지
            List<Integer> sortedPriorityList = new ArrayList<>(openParenthesisPriorityList);
            Collections.sort(sortedPriorityList);

            for (int i = 0; i < sortedPriorityList.size() - 1; i++) {
                if(sortedPriorityList.get(0) != 1){
                    throw new IllegalArgumentException("중첩을 위해서는 소괄호가 반드시 사용되어야합니다.");
                }else if (sortedPriorityList.get(i + 1) - sortedPriorityList.get(i) != 1) {
                    throw new IllegalArgumentException("우선순위가 높은 괄호부터 차례대로 사용해주세요");
                }
            }
        }

        if(rightParenthesisCount != leftParenthesisCount){
            System.out.println(rightParenthesisCount);
            System.out.println(leftParenthesisCount);

            throw new IllegalArgumentException("괄호의 중첩이 끝나지 않았습니다.");
        }




        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }

        return output;
    }

     */





}
