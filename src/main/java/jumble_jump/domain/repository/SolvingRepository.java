package jumble_jump.domain.repository;

import java.util.List;

public interface SolvingRepository {
    void saveSolving(String intermediateStep);
    void setResult(Double result);

    List<Solving> getSolving();
    Number getResult();


    int getTotalNumberOfSolve();
    void reset();
}
