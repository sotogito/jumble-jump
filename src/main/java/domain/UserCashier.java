package domain;

import domain.item.Items;
import util.validator.UserCashValidator;

import java.util.EnumMap;

public class UserCashier {
    private long amount;
    private ChangeCalculator changeCalculator;

    public UserCashier(long amount, Items items) {
        UserCashValidator.validate(amount,items.getMinimumPriceItem().getPrice());
        this.amount = amount;
        changeCalculator = new ChangeCalculator();
    }


    public long getAmount(){
        return amount;
    }

    public EnumMap<Bill,Integer> getChange(){
        return changeCalculator.calculateChange(amount);
    }

    public void validateSufficientAmount(long itemAmount) {
        if(itemAmount > this.amount) {
            long insufficientAmount = calculateInsufficientAmount(itemAmount);
            throw new IllegalArgumentException(
                    String.format("%,d원이 부족해요.", insufficientAmount)
            );
        }
    }

    public void decreaseAmountAsPurchased(long itemAmount) {
        this.amount -= itemAmount;
    }

    private long calculateInsufficientAmount(long itemAmount){
        return itemAmount - this.amount;
    }

    //note 더이상 구매할 수 있는지 확인
    public boolean isLessThanPurchasedItemAmount(long minimumItemPrice) {
        return amount < minimumItemPrice;
    }

    @Override
    public String toString() {
        return amount+"";
    }


}
