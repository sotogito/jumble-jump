package util.validator;

import util.Exception.InvalidItemRegistrationException;
import util.message.ErrorMessage;

public class ItemPriceValidator {
    private static final int MIN = 10;
    private static final long MAX = 10000000000L;

    public static void validate(long price){
        validateNumberInRange(price);
    }

    private static void validateNumberInRange(long number) {
        if(number < MIN || number > MAX) {
            throw new InvalidItemRegistrationException(
                    String.format(ErrorMessage.INVALID_REGISTER_ITEM_PRICE,MIN,MAX)
            );
        }
    }

}
