package domain;

import domain.item.Item;
import domain.item.Items;

public class Store {
    private Order order;
    private final Items items;
    private final UserCashier userCashier;
    private Item minimumPrice;

    public Store(Order order, Items items, UserCashier userCashier) {
        this.userCashier = userCashier;
        this.order = order;
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
        if(item.equals(minimumPrice) && minimumPrice.isOutOfStock()){ //만약 구매한 상품이 최소금액인데 수량이0이면 최소 가격 업데이트
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
