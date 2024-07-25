package jumble_jump.controller;

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
            if(calculatorService.isSolveProblemOnce(i)){
                printIntermediateStepAndCount(i);
            }
        }
        printResult();
    }

    private void printIntermediateStepAndCount(int order) {
        int numberOfSolving = calculatorService.getNumberOfSolving();
        String intermediateStep = calculatorService.getIntermediateStep(order);

        Output.printIntermediateStepAndCount(numberOfSolving,intermediateStep);
    }

    private void printResult(){
        calculatorService.setResult();
        Number result = DecimalPointFormatter.format(calculatorService.getResult());
        String problem = calculatorService.getProblem();
        int totalNumberOfSolving = calculatorService.getNumberOfSolving();

        Output.printResult(totalNumberOfSolving,problem,result);
    }

    private void printCalculateIntro(){
        Output.printServiceMessage("\n-------계산중-------\n");
    }

}
