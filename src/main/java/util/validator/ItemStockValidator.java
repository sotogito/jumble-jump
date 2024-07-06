package util.validator;

import util.message.ErrorMessage;

public class ItemStockValidator {
    private static final int MIN = 1;
    private static final int MAX = 1000;

    public static void validate(int stock){
        validateNumberInRange(stock);
    }

    private static void validateNumberInRange(long number) {
        if(number < MIN || number > MAX) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INVALID_REGISTER_ITEM_STOCK,MIN,MAX)
            );
        }
    }

}
