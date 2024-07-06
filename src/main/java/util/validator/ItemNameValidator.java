package util.validator;

import util.message.ErrorMessage;

public class ItemNameValidator {

    public static void validate(String itemName){
        validateNotEmpty(itemName);
    }

    private static void validateNotEmpty(String itemName) {
        if(itemName == null || itemName.isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.EMPTY_REGISTER_ITEM_NAME);
        }
    }

}
