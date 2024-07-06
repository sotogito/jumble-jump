package util.message;

public class ErrorMessage {
    public static final String ONLY_NUMBER = "숫자로 입력해주세요.\n";

    public static final String ONLY_NUMBER_PURCHASE_QUANTITY = "구매 수량은 숫자로 입력해주세요.\n";

    public static final String INVALID_USER_AMOUNT = "가격 투입은 %,d원부터 %,d원까지 입력 가능합니다.\n";
    public static final String INVALID_PURCHASE_ITEM_COMPONENT = "구매하고 싶은 상품과 이름과 수량을 콤마(,) 기준으로 적어주세요.\n";

    public static final String INVALID_PURCHASE_ITEM_QUANTITY = "%s는(은) 수량이 %d개 뿐이에요!\n";
    public static final String INVALID_ITEM_TOTAL_AMOUNT = "%,d원이 부족해요.\n";

    public static final String ALL_ITEM_OUT_OF_STOCK = "모든 상품이 소진되었어요. 장사 끝!\n";
    public static final String NOT_EXISTENCE_INPUT_ITEM = "존재하지 않는 상품입니다.\n";

    public static final String EMPTY_REGISTER_ITEM_NAME = "상품의 이름이 비어있습니다.\n";
    public static final String INVALID_REGISTER_ITEM_PRICE = "상품의 가격은 %d부터 %d까지 입력 가능합니다.\n";
    public static final String INVALID_REGISTER_ITEM_STOCK = "상품의 수량은 %d부터 %d까지 입력 가능합니다.\n";

}
