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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class ApplicationExpectTest {

    @Test
    void application1() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = "{1+1}";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("단독으로 사용하는 괄호는 소괄호를 사용하세요.", e.getMessage());
        }
    }

    @Test
    void application2() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = "[{1+1}]";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("중첩을 위해서는 소괄호가 반드시 사용되어야 합니다.", e.getMessage());
        }
    }


    @Test
    void application3() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = "[{(1+1)]";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("열린 괄호와 닫힌 괄호의 종류가 달라요. ex) {]", e.getMessage());
        }
    }

    @Test
    void application4() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = "*1+1";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("부호는 더하기와 빼기만 가능합니다.", e.getMessage());
        }
    }

    @Test
    void application6() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = "1+";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("문제 마지막 자리에 연산자가 올 수 없습니다.", e.getMessage());
        }
    }

    @Test
    void application7() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = ")1+1)";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("첫 괄호는 열린 괄호여야 합니다.", e.getMessage());
        }
    }

    @Test
    void application8() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();
        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter infixPostFixHelper = new ProblemToPostFixConverter(postfixExpressionManager);


        try{
            String inputProblem = "[(1+1)]";

            Tokenizer tokenizer = new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
            List<Token> tokens = tokenizer.tokenize(inputProblem);
            Problem problem = new Problem(tokens);
            Solving solving = new Solving();

            CalculatorService calculatorService = new CalculatorService(problem,solving,infixPostFixHelper);

            CalculateController calculateController =  new CalculateController(calculatorService);
            calculateController.calculate();

        }catch (IllegalArgumentException e){
            assertEquals("중첩 시 괄호의 우선순위를 지켜주세요.", e.getMessage());
        }
    }


}
