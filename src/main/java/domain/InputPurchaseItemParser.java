package domain;

import util.message.ErrorMessage;
import util.validator.InputPurchaseItemValidator;

public class InputPurchaseItemParser {

    public static String[] parse(String input) {
        String[] parts = input.split(",");
        InputPurchaseItemValidator.validate(parts);

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
