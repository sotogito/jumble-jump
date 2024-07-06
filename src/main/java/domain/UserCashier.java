package domain;

import domain.item.Items;
import util.message.ErrorMessage;
import util.validator.UserCashValidator;

import java.util.EnumMap;

public class UserCashier {
    private long amount;
    private final ChangeCalculator changeCalculator;

    public UserCashier(long amount, Items items) {
        UserCashValidator.validate(amount,items.getMinimumPriceItem().getPrice());
        this.amount = amount;
        changeCalculator = new ChangeCalculator();
    }

    public void validateSufficientAmount(long itemAmount) {
        if(itemAmount > this.amount) {
            long insufficientAmount = calculateInsufficientAmount(itemAmount);
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INVALID_ITEM_TOTAL_AMOUNT, insufficientAmount)
            );
        }
    }

    public boolean isLessThanPurchasedItemAmount(long minimumItemPrice) {
        return amount < minimumItemPrice;
    }

    public void decreaseAmountAsPurchased(long itemAmount) {
        this.amount -= itemAmount;
    }

    private long calculateInsufficientAmount(long itemAmount){
        return itemAmount - this.amount;
    }

    public long getAmount(){
        return amount;
    }

    public EnumMap<Bill,Integer> getChange(){
        return changeCalculator.calculateChange(amount);
    }

    @Override
    public String toString() {
        return amount+"";
    }

}
