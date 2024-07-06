package domain.item;

import domain.item.component.Name;
import domain.item.component.Price;
import domain.item.component.Stock;

public class Item {

    private final Name name;
    private final Price price;
    private final Stock stock;

    public Item(String name, long price, int stock) {
        this.name = new Name(name);
        this.price = new Price(price);
        this.stock = new Stock(stock);
    }

    //note 수량이 부족하지 않은지
    public void validateAfterPurchasingStock(int quantity){
        int itemStock = stock.getStock();
        if(itemStock < quantity){
            throw new IllegalArgumentException(
                    String.format("%s는(은) 수량이 %d개 뿐이에요!",name.getName(),itemStock)
            );
        }
    }

    //note 구매 가격이 충분 한지


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
