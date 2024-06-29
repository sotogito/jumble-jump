package random_word_test.util.validators;

import random_word_test.util.message.ErrorMessage;

import java.util.List;

public class EnglishValidator {

    public static void validate(String word) {
        validateAlphabetic(word);

    }

    public static void validateAlphabetic(String word) {
        if (!word.matches("^[A-Za-z]+$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VOCA_ENTRY);
        }
    }

}
