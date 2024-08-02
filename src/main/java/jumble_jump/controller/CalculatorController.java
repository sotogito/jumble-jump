package jumble_jump.controller;

import jumble_jump.domain.repository.Solving;
import jumble_jump.domain.repository.SolvingRepository;
import jumble_jump.domain.repository.SolvingRepositoryImpl;
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
import jumble_jump.domain.repository.Problem;

import java.util.List;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final Tokenizer tokenizer;

    @Autowired
    public CalculatorController(CalculatorService calculatorService,Tokenizer tokenizer) {
        this.calculatorService = calculatorService;
        this.tokenizer = tokenizer;
    }

    @PostMapping("/calculate")
    public void calculate(@RequestParam("problem") String problemText) {

        //Tokenizer tokenizer = createTokenizer();
        List<Token> tokens = tokenizer.tokenize(problemText);
        Problem problem = new Problem(tokens);

        //PostfixExpressionManager postfixExpressionManager = new PostfixExpressionManager();
        //ProblemToPostFixConverter problemToPostFixConverter = new ProblemToPostFixConverter(postfixExpressionManager);
        //SolvingRepository solvingRepository = new SolvingRepositoryImpl();
        //CalculatorService calculatorService = new CalculatorService(solvingRepository, problemToPostFixConverter);

        calculatorService.setProblem(problem);
        calculatorService.calculate();


        for(Solving solving : calculatorService.getSolvingList()) {
            System.out.println("풀이 횟수 : "+solving.getNumberOfSolving());
            System.out.println("중간식 : "+solving.getIntermediateStep());
        }

        System.out.println(tokens.size()+"토큰사이즈");
        System.out.println("총 풀이 횟수 : "+calculatorService.getSolvingList().size());
        System.out.println("결과 : "+calculatorService.getResult());

    }



    private Tokenizer createTokenizer() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();

        return new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
    }
}
