package domain;

import util.validator.UserCashValidator;

public class UserCashier {
    private long amount;

    public UserCashier(long amount) {
        UserCashValidator.validate(amount);
        this.amount = amount;
    }

    /*
    public long getAmount() {
        return amount;
    }

     */

    public void decreaseAmountAsPurchased(long itemAmount) {
        if(itemAmount > this.amount) {
            long insufficientAmount = calculateInsufficientAmount(itemAmount);
            throw new IllegalArgumentException(
                    String.format("%,d원이 부족해요.", insufficientAmount)
            );
        }
        this.amount -= itemAmount;
    }

    private long calculateInsufficientAmount(long itemAmount){
        return itemAmount - this.amount;
    }

    //note 더이상 구매할 수 있는지 확인
    public boolean isLessThanPurchasedItemAmount(long minimumItemPrice) {
        return amount < minimumItemPrice;
    }


}
