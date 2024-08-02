package jumble_jump.domain.repository;

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
