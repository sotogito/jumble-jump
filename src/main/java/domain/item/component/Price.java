package domain.item.component;

import util.validator.ItemPriceValidator;

public class Price {

    private final int price;

    public Price(int price) {
        ItemPriceValidator.validate(price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
