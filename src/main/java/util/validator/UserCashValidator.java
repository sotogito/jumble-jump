package util.validator;

public class UserCashValidator {

    private static final int MIN = 100;
    private static final long MAX = 100000000;

    public static void validate(long amount){
        validateNumberInRange(amount);
    }

    private static void validateNumberInRange(long number) {
        if(number < MIN || number > MAX) {
            throw new IllegalArgumentException(
                    String.format("가격 투입은 %d부터 %d까지 입력 가능합니다.",MIN,MAX)
            );
        }
    }

}
