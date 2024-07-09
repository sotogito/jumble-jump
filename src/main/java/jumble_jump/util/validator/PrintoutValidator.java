package jumble_jump.util.validator;

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
            throw new IllegalArgumentException("1~25자까지 입력할 수 있어요.\n");
        }
    }

    /**
     * 한국어로 입력되면 안되는데.. 그렇다고한국어로 입력되지 않으면 예외를 잡자기에는
     * 다른나라 언어도 입력이 불가하다.
     *
     * 영어로만 입력이 되어있나? 확인하려면 특수문자도 입력이 가능한다..
     * 만약 안되는 특수문자가 있으면 표시하고 출력하기
     */

    private static void validateString(String input) {
        if (!Pattern.matches(PATTERN, input)) {
            throw new IllegalArgumentException("출력물은 영어와 숫자, 특수문자만 입력 가능합니다.\n");
        }
    }

}
