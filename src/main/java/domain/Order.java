package domain;

import domain.item.Item;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Item, Integer> purchasedItems;
    private long totalAmount;

    Order() {
        this.purchasedItems = new HashMap<>();
    }

    public void updatePurchasedItemsAndAmount(Item item, int purchasedQuantity) {
        purchasedItems.put(item, purchasedQuantity);
        totalAmount += item.calculatePurchasePriceAsAmount(purchasedQuantity);
    }

    public Map<Item, Integer> getPurchasedItems() {
        return purchasedItems;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

}
