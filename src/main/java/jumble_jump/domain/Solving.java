package jumble_jump.domain;

import jumble_jump.util.Token;
import jumble_jump.util.converter.ProblemToStringConverter;

import java.util.ArrayList;
import java.util.List;

//todo 정적?
public class Solving {
    private int numberOfSolving = 0;

    public Solving() {
    }

    public void updateNumberOfSolving() {
        numberOfSolving++;
    }

    public int getNumberOfSolving() {
        return numberOfSolving;
    }



}
