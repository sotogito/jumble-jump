package jumble_jump.domain;

import jumble_jump.domain.repository.Solving;
import jumble_jump.domain.repository.SolvingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SolvingRepositoryImpl implements SolvingRepository {

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

    public void reset() {
        solvings.clear();  // 모든 Solving 객체를 삭제
        sequence = 0;      // 식별 번호를 0으로 초기화
    }


}
