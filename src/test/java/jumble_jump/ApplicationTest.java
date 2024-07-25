package jumble_jump;

import jumble_jump.controller.CalculateController;
import jumble_jump.controller.MainController;
import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.helper.InfixPostFixHelper;
import jumble_jump.domain.helper.PostfixExpressionManager;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.service.CalculatorService;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ApplicationTest {

    @Test
    void application() {

        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        InfixPostFixHelper infixPostFixHelper = new InfixPostFixHelper(postfixExpressionManager);

        String inputProblem = "1+1";
        double expected = 2;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }



}