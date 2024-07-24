package jumble_jump.controller;

import jumble_jump.service.CalculatorService;

public class CalculateController {

    private CalculatorService calculatorService;

    public CalculateController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public void calculate() {
        for (int i = 0; i < calculatorService.getPostfixSize()-1; i++) {
            if(calculatorService.isSolveProblemOnce(i)){
                System.out.println("중간식"+calculatorService.getIntermediateStep(i));
            }
        }
        calculatorService.setResult();
        System.out.println("정답"+calculatorService.getResult());


    }




}
