package jumble_jump.domain.repository;

import jumble_jump.util.DecimalPointFormatter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 가장 마지마깅 정답?
 */
@Repository
public class SolvingRepositoryImpl implements SolvingRepository {

    private Double result = 0.0;
    private final List<Solving> solving = new ArrayList<>();
    private static int sequence = 0;

    public void saveSolving(String intermediateStep){
        solving.add(new Solving(intermediateStep, ++sequence));
    }

    public void setResult(Double result){
        this.result = result;
    }

    public List<Solving> getSolving() {
        return solving;
    }

    public Number getResult() {
        return DecimalPointFormatter.format(result);
    }

    public int getTotalNumberOfSolve(){
        return solving.size();
    }

    public void reset() {
        solving.clear();  // 모든 Solving 객체를 삭제
        sequence = 0;      // 식별 번호를 0으로 초기화
    }


}
