package random_word_test.util.validators;

import random_word_test.util.message.ErrorMessage;

import java.util.List;

public class EnglishValidator {

    public static void validate(String word) {
        validateAlphabetic(word);

    }

    public static void validateAlphabetic(String word) {
        if (!word.matches("^[A-Za-z\\s]+$")) { // 알파벳과 공백을 허용하도록 정규 표현식 수정
            throw new IllegalArgumentException(ErrorMessage.INVALID_VOCA_ENTRY);
        }
    }

}
