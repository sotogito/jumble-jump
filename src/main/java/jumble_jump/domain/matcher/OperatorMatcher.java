package jumble_jump.domain.matcher;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.operator.*;
import jumble_jump.util.Token;

import java.util.ArrayList;
import java.util.List;

public class OperatorMatcher implements Matcher {

    private final List<OperatorToken> operators = new ArrayList<>();

    public OperatorMatcher() {
        initOperator();
    }

    //note 반환은 Token으로 함
    @Override
    public Token match(char inputOperator){
        OperatorType operatorType = OperatorType.fromSymbol(inputOperator);

        for(OperatorToken operator : operators) {
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
