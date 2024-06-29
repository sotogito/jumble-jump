package random_word_test.util.validators;

public class CommandFormatValidator {

    public static boolean isContainCommandFormat(String input) {
        return input.matches("\\[.*\\]");
    }
}
