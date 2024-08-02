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
import org.springframework.ui.Model;
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
    public String calculate(@RequestParam("problem") String problemText, Model model) {

        List<Token> tokens = tokenizer.tokenize(problemText);
        Problem problem = new Problem(tokens);

        calculatorService.setProblem(problem);
        calculatorService.calculate();

        model.addAttribute("problem", calculatorService.getProblemText());
        model.addAttribute("postfix",calculatorService.getProblemPostFix());
        model.addAttribute("tokensSize", tokens.size());
        model.addAttribute("solvingList", calculatorService.getSolvingList());
        model.addAttribute("totalSolvingCount", calculatorService.getTotalNumberOfSolving());
        model.addAttribute("result", calculatorService.getResult());

        return "result"; // 결과를 출력할 뷰의 이름
    }

}
