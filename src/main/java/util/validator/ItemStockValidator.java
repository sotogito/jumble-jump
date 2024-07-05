package util.validator;

public class ItemStockValidator {

    private static final int MIN = 1;
    private static final int MAX = 1000;

    public static void validate(int stock){
        validateNumberInRange(stock);
    }

    private static void validateNumberInRange(long number) {
        if(number < MIN || number > MAX) {
            throw new IllegalArgumentException(
                    String.format("상품의 수량은 %d부터 %d까지 입력 가능합니다.",MIN,MAX)
            );
        }
    }


}
