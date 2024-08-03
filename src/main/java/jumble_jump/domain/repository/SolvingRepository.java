package jumble_jump.domain.repository;

import jumble_jump.domain.Resettable;

import java.util.List;

public interface SolvingRepository extends Resettable {
    void saveSolving(String intermediateStep);
    void setResult(Double result);

    List<Solving> getSolving();
    Number getResult();


    int getTotalNumberOfSolve();
}
