package jumble_jump.util.validator.tokenizer;

import jumble_jump.domain.type.OperatorType;

public class OperatorTokenizeValidator {

    public static boolean isNumberSign(char c){
        if(c == OperatorType.SUBTRACT.getSymbol() || c == OperatorType.ADD.getSymbol()){
            return true;
        }
        throw new IllegalArgumentException("부호는 더하기와 빼기만 가능합니다.");
    }

    public static void validateLastOperator(int i, int length){
        if(i == length-1){
            throw new IllegalArgumentException("문제 마지막 자리에 연산자가 올 수 없습니다.");
        }
    }

}
