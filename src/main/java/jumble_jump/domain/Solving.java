package jumble_jump.domain;

import jumble_jump.util.Token;
import jumble_jump.util.converter.ProblemToStringConverter;

import java.util.ArrayList;
import java.util.List;

//todo 정적?
public class Solving {
    private int numberOfSolving = 0;
    private List<Token> intermediateStep = new ArrayList<>();

    public Solving() {
    }

    public void updateNumberOfSolving() {
        numberOfSolving++;
    }

    public void updateIntermediateStep(List<Token> intermediateProblem) {
        intermediateStep = intermediateProblem;
    }

    public int getNumberOfSolving() {
        return numberOfSolving;
    }

    public String getIntermediateStepText(){
        return ProblemToStringConverter.getProblemText(intermediateStep);
    }

    public boolean isOverSolve(){
        return intermediateStep.size() == 1;
    }







}
