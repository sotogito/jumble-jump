package jumble_jump.controller;

import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.service.CalculatorService;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.helper.PostfixExpressionManager;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class MainController {

    public void main(){
        Tokenizer tokenizer = createTokenizer();
        List<Token> tokens = getTokens(tokenizer);
        Problem problem = createProblem(tokens);
        Solving solving = createSolving();

        CalculatorService calculatorService = createCalculatorService(problem,solving);

        CalculateController calculateController = new CalculateController(calculatorService);
        calculateController.calculate();


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

    private List<Token> getTokens(Tokenizer tokenizer) {
        while (true){
            try{
                return tokenizer.tokenize(Input.inputProblem());
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
            }
        }
    }

    private Tokenizer createTokenizer() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();

        return new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
    }
}
