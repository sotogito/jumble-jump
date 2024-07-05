package util.validator;

public class ItemNameValidator {

    public static void validate(String itemName){
        validateNotEmpty(itemName);
    }

    private static void validateNotEmpty(String itemName) {
        if(itemName == null || itemName.isEmpty()){
            throw new IllegalArgumentException("");
        }
    }

}
