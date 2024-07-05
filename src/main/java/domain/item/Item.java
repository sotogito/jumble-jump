package domain.item;

import domain.item.component.Name;
import domain.item.component.Price;
import domain.item.component.Stock;

public class Item {

    private final Name name;
    private final Price price;
    private final Stock stock;

    public Item(String name, int price, int stock) {
        this.name = new Name(name);
        this.price = new Price(price);
        this.stock = new Stock(stock);
    }

    public void decreaseStock(int amount) {
        stock.decreaseStock(amount);
    }

    public boolean isOutOfStock(){
        return stock.getStock() <= 0;
    }

    public String getName() {
        return name.getName();
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getStock() {
        return stock.getStock();
    }

}
