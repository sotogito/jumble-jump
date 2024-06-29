package random_word_test.util.validators;

import random_word_test.util.message.ErrorMessage;

import java.util.List;

public class KoreanValidator {

    public static void validate(List<String> definitions) {
        validateAllKorean(definitions);

    }

    public static void validateAllKorean(List<String> definitions) {
        boolean allKorean = definitions.stream()
                .allMatch(part -> part.matches("^[가-힣\\s]+$")); // 한글과 공백을 허용하도록 정규 표현식 수정

        if (!allKorean) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VOCA_ENTRY);
        }
    }
}
