package domain.item.component;

import util.validator.ItemStockValidator;

public class Stock {
    private int stock;

    public Stock(int stock) {
        ItemStockValidator.validate(stock);
        this.stock = stock;
    }

    public void decreaseStock(int amount) {
        stock -= amount;
    }

    public int getStock() {
        return stock;
    }

}
