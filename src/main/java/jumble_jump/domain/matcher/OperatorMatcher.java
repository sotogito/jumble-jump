package jumble_jump.domain.matcher;

import jumble_jump.domain.component.OperatorType;
import jumble_jump.domain.operators.*;

import java.util.ArrayList;
import java.util.List;

public class OperatorMatcher {

    private final List<Operator> operators = new ArrayList<>();

    public OperatorMatcher() {
        initOperator();
    }

    private Operator match(char inputOperator){
        OperatorType operatorType = OperatorType.fromSymbol(inputOperator);

        for(Operator operator : operators) {
            if(operator.getOperatorType().equals(operatorType)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("찾을 수 없는 연산자입니다");
    }


    private void initOperator(){
        operators.add(new AddOperator());
        operators.add(new SubOperator());
        operators.add(new MulOperator());
        operators.add(new DivOperator());
    }
}
