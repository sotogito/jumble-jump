package util.validator;

public class ItemPriceValidator {

    private static final int MIN = 10;
    private static final long MAX = 10000000000L;

    public static void validate(long price){
        validateNumberInRange(price);
    }

    private static void validateNumberInRange(long number) {
        if(number < MIN || number > MAX) {
            throw new IllegalArgumentException(
                    String.format("상품의 가격은 %d부터 %d까지 입력 가능합니다.",MIN,MAX)
            );
        }
    }

}
