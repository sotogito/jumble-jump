package jumble_jump.controller;

import jumble_jump.service.CalculatorService;
import jumble_jump.util.DecimalPointFormatter;

public class CalculateController {

    private CalculatorService calculatorService;

    public CalculateController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public void calculate() {
        for (int i = 0; i < calculatorService.getPostfixSize(); i++) {
            if(calculatorService.isSolveProblemOnce(i)){
                System.out.println("풀이 횟수 : "+calculatorService.getNumberOfSolving());
                System.out.println("중간식 : "+calculatorService.getIntermediateStep(i));
                System.out.println();
            }
        }
        System.out.println("--------------");
        calculatorService.setResult();
        Number result = DecimalPointFormatter.format(calculatorService.getResult());
        System.out.println("정답 : "+result);


    }




}
