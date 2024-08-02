package jumble_jump.controller;

import jumble_jump.domain.repository.Solving;
import jumble_jump.domain.repository.SolvingRepository;
import jumble_jump.domain.SolvingRepositoryImpl;
import jumble_jump.domain.converter.ProblemToPostFixConverter;
import jumble_jump.domain.manager.PostfixExpressionManager;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.service.CalculatorService;
import jumble_jump.domain.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jumble_jump.domain.converter.token.Tokenizer;
import jumble_jump.domain.Problem;

import java.util.List;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public void calculate(@RequestParam("problem") String problemText) {
        /**
         * 스프링 어노테이션 사용하지 말고 해야됳긋
         * ProblemToPostFixConverter에서 계속 오류남
         * solve사이즈 이사함
         */

        /*
        Tokenizer tokenizer = createTokenizer();
        List<Token> tokens = tokenizer.tokenize(problemText);
        Problem problem = new Problem(tokens);

        calculatorService.setProblem(problem);
        calculatorService.calculate();

        for(Solving solving : calculatorService.getSolvings()) {
            System.out.println(solving.getNumberOfSolving());
            System.out.println(solving.getIntermediateStep());
        }

        System.out.println(tokens.size()+"tkdlwm");
        System.out.println(calculatorService.getSolvings().size());

         */


        Tokenizer tokenizer = createTokenizer();
        List<Token> tokens = tokenizer.tokenize(problemText);
        Problem problem = new Problem(tokens);

        PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        ProblemToPostFixConverter problemToPostFixConverter = new ProblemToPostFixConverter(postfixExpressionManager);
        SolvingRepository solvingRepository = new SolvingRepositoryImpl();
        CalculatorService calculatorService = new CalculatorService(solvingRepository, problemToPostFixConverter);

        calculatorService.setProblem(problem);
        calculatorService.calculate();

        for(Solving solving : calculatorService.getSolvings()) {
            System.out.println(solving.getNumberOfSolving());
            System.out.println(solving.getIntermediateStep());
        }

        System.out.println(tokens.size()+"tkdlwm");
        System.out.println(calculatorService.getSolvings().size());
        System.out.println(calculatorService.getResult());

    }

    private Tokenizer createTokenizer() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();

        return new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
    }
}
