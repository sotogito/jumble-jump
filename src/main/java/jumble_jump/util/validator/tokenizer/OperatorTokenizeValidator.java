package jumble_jump.util.validator.tokenizer;

import jumble_jump.domain.type.OperatorType;

public class OperatorTokenizeValidator {

    public static boolean isNumberSign(char c){
        if(c == OperatorType.SUBTRACT.getSymbol() || c == OperatorType.ADD.getSymbol()){
            return true;
        }
        throw new IllegalArgumentException("첫자리 연산자는 더하기와 곱하기만 가능합니다.");
    }

    public static void validateLastOperator(int i, int length){
        if(i == length-1){
            throw new IllegalArgumentException("마지막에 연산자가가 올 수 없습니다.");
        }
    }
}
