package domain;

import domain.item.Item;
import domain.item.Items;

public class Store {
    private Order order;
    private final Items items;
    private final UserCashier userCashier;
    private Item minimumPrice;

    //돈은 부족하지 않은데 수량이 부족하면? 돈은 업데이트 된거잖아
    //수량은있는데 돈이 부족하면? 수량은 업데이트 된거잖아
    //각각 유효처리 한거를 따로 분리해야될듯

    public Store(Items items, UserCashier userCashier) {
        this.userCashier = userCashier;
        this.order = new Order();
        this.items = items;
        this.minimumPrice = items.getMinimumPriceItem();
    }

    //todo 테스트 코드 작성 후 메서드 분리
    public boolean isCanPurchase(String itemName, int quantity){
        Item item = items.findItemByName(itemName);
        long purchasedPrice = item.calculatePurchasePriceAsAmount(quantity);

        validateBeforePurchase(item,quantity,purchasedPrice);
        updatePurchases(item,quantity,purchasedPrice);


        //note 구입 후에 확인해야하는 사항(앞으로) : 상품 전원 품절, 최소 금액보다 잔돈이 많은지
        if(item.equals(minimumPrice) && minimumPrice.isOutOfStock()){//구매했는데 만약 상품이 0개라면 최
            try{
                minimumPrice = items.getMinimumPriceItem();
            }catch (IllegalArgumentException e){
                return false;
            }
        }
        //note 최소 금액보다 일단 잔돈이 많은지
        if(userCashier.isLessThanPurchasedItemAmount(minimumPrice.getPrice())){
            return false;
        }

        return true;
    }

    //note 구입 전에 확인해야하는 사항 : 일단 구매 하면 구매할 돈이 있고, 충분한 수량이 있는지 확인
    private void validateBeforePurchase (Item item, int quantity, long purchasedPrice){
        item.validateAfterPurchasingStock(quantity);
        userCashier.validateSufficientAmount(purchasedPrice);
    }


    //note 성공하면 업데이트
    private void updatePurchases(Item item, int quantity, long purchasedPrice){
        userCashier.decreaseAmountAsPurchased(purchasedPrice);
        item.decreaseStock(quantity);
        order.updatePurchasedItemsAndAmount(item,quantity);
    }
}
