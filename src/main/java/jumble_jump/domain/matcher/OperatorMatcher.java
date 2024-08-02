package jumble_jump.domain.matcher;

import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.operator.*;
import jumble_jump.domain.token.Token;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OperatorMatcher implements Matcher{

    private final List<OperatorToken> operators = new ArrayList<>();

    public OperatorMatcher() {
        register();
    }

    public Token match(char inputOperator){
        OperatorType operatorType = OperatorType.fromSymbol(inputOperator);

        for(OperatorToken operator : operators) {
            if(operator.getOperatorType().equals(operatorType)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("찾을 수 없는 연산자입니다");
    }

    public void register(){
        operators.add(new AddOperator());
        operators.add(new SubOperator());
        operators.add(new MulOperator());
        operators.add(new DivOperator());
    }

}
