package jumble_jump.util.validator;

import jumble_jump.util.message.ErrorMessage;

import java.util.regex.Pattern;

public class PrintoutValidator {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 50;
    private static final String PATTERN = "^[a-zA-Z0-9!@#$%^&*(),.?\":{}|<> ]*$";

    public static void validate(String input){
        validateNotEmptyAndLength(input);
        validateString(input);

    }

    private static void validateNotEmptyAndLength(String input) {
        if(input.length()<MIN_LENGTH || input.length() > MAX_LENGTH){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_PRINTOUT_LENGTH);
        }
    }

    private static void validateString(String input) {
        if (!Pattern.matches(PATTERN, input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_PRINTOUT_STYLE);
        }
    }

}
