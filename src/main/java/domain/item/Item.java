package domain.item;

import domain.item.component.Name;
import domain.item.component.Price;
import domain.item.component.Stock;
import util.Exception.InvalidPurchaseException;
import util.message.ErrorMessage;

public class Item {
    private final Name name;
    private final Price price;
    private final Stock stock;

    public Item(String name, long price, int stock) {
        this.name = new Name(name);
        this.price = new Price(price);
        this.stock = new Stock(stock);
    }

    public void validateAfterPurchasingStock(int quantity){
        int itemStock = stock.getStock();
        if(itemStock < quantity){
            throw new InvalidPurchaseException(
                    String.format(ErrorMessage.INVALID_PURCHASE_ITEM_QUANTITY,name.getName(),itemStock)
            );
        }
    }

    public long calculatePurchasePriceAsAmount(int purchasedQuantity) {
        return price.getPrice() * purchasedQuantity;
    }

    public void decreaseStock(int purchasedQuantity) {
        stock.decreaseStock(purchasedQuantity);
    }

    public boolean isOutOfStock(){
        return stock.getStock() <= 0;
    }

    public String getName() {
        return name.getName();
    }

    public long getPrice() {
        return price.getPrice();
    }

    public int getStock() {
        return stock.getStock();
    }

    @Override
    public String toString() {
        return name.getName() + " : " + price.getPrice()+"\n";
    }

}
