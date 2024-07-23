package jumble_jump.util.validator;

public class NumberValidator {

    private final static int MIN = 0;
    private final static int MAX = 100;

    public static void validate(double number){
        //validateNumberInRange(number);
    }

    private static void validateOnlyNumeric(String str) {
        if (!str.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("계산은 숫자만 가능합니다.");
        }
    }

    private static void validateNumberInRange(double number) {
        if(number < MIN || number > MAX) {
            throw new IllegalArgumentException(
                    String.format("계산 숫자는 %d부터 %d까지 입력 가능합니다.",MIN,MAX));
        }
    }


}
