package jumble_jump.domain;

import java.util.ArrayList;
import java.util.List;

public class SolvingRepository {

    private List<Solving> solvings = new ArrayList<>();
    private static int sequence = 0;

    public void saveSolving(String intermediateStep){
        solvings.add(new Solving(intermediateStep, ++sequence));
    }

    public List<Solving> getSolvings() {
        return solvings;
    }

    public int getTotalNumberOfSolve(){
        return solvings.size();
    }
}
