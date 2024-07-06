package util.validator;

import util.message.ErrorMessage;

public class UserCashValidator {

    private static long MIN;
    private static final long MAX = 100000000;

    public static void validate(long amount,long minimumItemPrice){
        MIN = minimumItemPrice;
        validateNumberInRange(amount);
    }

    private static void validateNumberInRange(long number) {
        if(number < MIN || number > MAX) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INVALID_USER_AMOUNT,MIN,MAX)
            );
        }
    }

}
