package jumble_jump.domain;

import jumble_jump.util.Token;
import jumble_jump.util.converter.ProblemToStringConverter;

import java.util.ArrayList;
import java.util.List;

//todo 정적?
public class Solving {
    private int numberOfSolving;
    private final String intermediateStep;

    public Solving(String intermediateStep, int numberOfSolving) {
        this.intermediateStep = intermediateStep;
        this.numberOfSolving = numberOfSolving;
    }

    public int getNumberOfSolving() {
        return numberOfSolving;
    }

    public String getIntermediateStep() {
        return intermediateStep;
    }

}
