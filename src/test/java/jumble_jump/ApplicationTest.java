package jumble_jump;

import jumble_jump.controller.CalculateController;
import jumble_jump.domain.Problem;
import jumble_jump.domain.Solving;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.manager.PostfixExpressionManager;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.service.CalculatorService;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ApplicationTest {

    @Test
    void application1() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "({5 - 3} * [2 + (3 - 1)])";
        double expected = 8;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }

    @Test
    void application2() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "-100+99";
        double expected = -1;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }

    @Test
    void application3() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "-(100-99)";
        double expected = -1;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }

    @Test
    void application4() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "(100-99) - -1";
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


    @Test
    void application5() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "7(1+1)";
        double expected = 14;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }

    @Test
    void application6() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "-7(1+1)";
        double expected = -14;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }

    @Test
    void application7() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "-{2(1+1)}";
        double expected = -4;

        Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
        List<Token> tokens = tokenizer.tokenize(inputProblem);
        Problem problem = new Problem(tokens);
        Solving solving = new Solving();

        CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

        CalculateController calculateController =  new CalculateController(calculatorService);
        calculateController.calculate();

        assertEquals(expected, calculatorService.getResult());
    }

    @Test
    void application8() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);

        String inputProblem = "[{3 * (4 + 2)} - 5] / 2";
        double expected = 6.5;

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