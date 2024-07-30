package jumble_jump.controller;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.SolvingRepository;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.service.CalculatorService;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.manager.PostfixExpressionManager;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class MainController {

    private CalculateController calculateController;

    public void main(){

        while (true){
            try{
                Tokenizer tokenizer = createTokenizer();
                List<Token> tokens = getTokens(tokenizer,inputProblem());
                Problem problem = createProblem(tokens);
                CalculatorService calculatorService = createCalculatorService(problem);

                calculateController = createCalculateController(calculatorService);
                calculateController.calculate();
                break;
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private CalculateController createCalculateController(CalculatorService calculatorService){
        return new CalculateController(calculatorService);
    }

    private CalculatorService createCalculatorService(Problem problem) {
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter problemToPostFixConverter = new ProblemToPostFixConverter(postfixExpressionManager);
        SolvingRepository solvingRepository = new SolvingRepository();

        return new CalculatorService(problem,solvingRepository,problemToPostFixConverter);
    }


    private Problem createProblem(List<Token> tokens) {
        return new Problem(tokens);
    }

    private List<Token> getTokens(Tokenizer tokenizer, String inputProblem) {
        return tokenizer.tokenize(inputProblem);
    }

    private String inputProblem(){
        return Input.inputProblem();
    }

    private Tokenizer createTokenizer() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();

        return new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
    }

}
