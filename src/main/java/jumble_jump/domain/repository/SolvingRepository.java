package jumble_jump.domain.repository;

import java.util.List;

public interface SolvingRepository {
    void saveSolving(String intermediateStep);
    List<Solving> getSolvings();
    int getTotalNumberOfSolve();
    void reset();
}
