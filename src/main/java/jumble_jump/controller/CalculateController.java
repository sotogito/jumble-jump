package jumble_jump.controller;

import jumble_jump.domain.Solving;
import jumble_jump.service.CalculatorService;
import jumble_jump.util.DecimalPointFormatter;
import jumble_jump.view.Output;

public class CalculateController {

    private CalculatorService calculatorService;

    public CalculateController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public void calculate() {
        printCalculateIntro();
        for (int i = 0; i < calculatorService.getPostfixSize(); i++) {
            calculatorService.isSolveProblemOnce(i);
        }
        printIntermediateStepAndCount();
        printResult();
    }

    private void printIntermediateStepAndCount() {
        for(Solving solving : calculatorService.getSolvings()) {
            System.out.println(solving.getNumberOfSolving());
            System.out.println(solving.getIntermediateStep());
        }
    }

    private void printResult(){
        calculatorService.setResult();
        Number result = DecimalPointFormatter.format(calculatorService.getResult());
        String problem = calculatorService.getProblem();
        int totalNumberOfSolving = calculatorService.getTotalNumberOfSolvings();

        Output.printResult(totalNumberOfSolving,problem,result);
    }

    private void printCalculateIntro(){
        Output.printServiceMessage("\n-------계산중-------\n");
    }

}
