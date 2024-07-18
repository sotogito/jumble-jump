package jumble_jump.domain;

import jumble_jump.util.Token;
import jumble_jump.util.converter.ProblemToStringConverter;

import java.util.ArrayList;
import java.util.List;

public class Solving {
    private static int numberOfSolving = 0;
    private static List<Token> intermediateStep = new ArrayList<>();

    public static void updateNumberOfSolving() {
        numberOfSolving++;
    }

    public static void updateIntermediateStep(List<Token> intermediateProblem) {
        intermediateStep = intermediateProblem;
    }

    public static int getNumberOfSolving() {
        return numberOfSolving;
    }

    public static String getIntermediateStepText(){
        return ProblemToStringConverter.getProblemText(intermediateStep);
    }







}
