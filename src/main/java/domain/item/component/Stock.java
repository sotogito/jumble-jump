package domain.item.component;

import util.validator.ItemStockValidator;

public class Stock {
    private int stock;

    public Stock(int stock) {
        ItemStockValidator.validate(stock);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
}
