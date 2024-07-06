package util.validator;

import util.message.ErrorMessage;

public class InputPurchaseItemValidator {

    public static void validate(String[] input){
        validateContainTwoValues(input);
        validateQuantityOnlyNumber(input);
    }

    private static void validateContainTwoValues(String[] input){
        if(input.length != 2){
            throw new ArrayIndexOutOfBoundsException(ErrorMessage.INVALID_PURCHASE_ITEM_COMPONENT);
        }
    }

    private static void validateQuantityOnlyNumber(String[] input){
        try{
            Integer.parseInt(input[1]);
        }catch(NumberFormatException e){
            throw new ArrayIndexOutOfBoundsException(ErrorMessage.ONLY_NUMBER_PURCHASE_QUANTITY);
        }
    }


}
