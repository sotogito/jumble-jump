package domain.item;

import java.util.Comparator;
import java.util.Iterator;
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
                .orElseThrow(() -> new IllegalArgumentException("모든 상품이 소진되었어요. 장사 끝!"));

    }

    public Item findItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 상품입니다.");
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
