package jumble_jump.controller;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.service.CalculatorService;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.helper.PostfixExpressionManager;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class MainController {

    private CalculateController calculateController;

    public void main(){
        Tokenizer tokenizer = createTokenizer();

        while (true){
            try{
                List<Token> tokens = getTokens(tokenizer,inputProblem());
                Problem problem = createProblem(tokens);
                Solving solving = createSolving();
                CalculatorService calculatorService = createCalculatorService(problem,solving);

                calculateController = createCalculateController(calculatorService);
                calculateController.calculate();
                break;
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
            }
        }
    }

    private CalculateController createCalculateController(CalculatorService calculatorService){
        return new CalculateController(calculatorService);
    }

    private CalculatorService createCalculatorService(Problem problem, Solving solving) {
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        InfixPostFixHelper infixPostFixHelper = new InfixPostFixHelper(postfixExpressionManager);

        return new CalculatorService(problem,solving,infixPostFixHelper);
    }

    private Solving createSolving() {
        return new Solving();
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
