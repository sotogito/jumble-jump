package domain.item.component;

import util.validator.ItemPriceValidator;

public class Price {

    private final long price;

    public Price(long price) {
        ItemPriceValidator.validate(price);
        this.price = price;
    }

    public long getPrice() {
        return price;
    }
}
