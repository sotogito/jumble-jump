package domain;

import util.message.ErrorMessage;

public class InputPurchaseItemParser {

    public static String[] parse(String input) {
        String[] parts = input.split(",");
        if(parts.length != 2) {
            throw new ArrayIndexOutOfBoundsException(ErrorMessage.INVALID_PURCHASE_ITEM_COMPONENT);
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
