package domain.item;

import util.Exception.InvalidPurchaseException;
import util.message.ErrorMessage;

import java.util.Comparator;
import java.util.List;

public class Items {
    private List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public Item getMinimumPriceItem(){
        return items.stream()
                .filter(item -> !item.isOutOfStock()) // item.getStock() > 0
                .min(Comparator.comparingDouble(Item::getPrice))
                .orElseThrow(() -> new InvalidPurchaseException(ErrorMessage.ALL_ITEM_OUT_OF_STOCK));

    }

    public Item findItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new InvalidPurchaseException(ErrorMessage.NOT_EXISTENCE_INPUT_ITEM);
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString());
        }
        return sb.toString();
    }

}
