package domain.item;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Items {

    private List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public int getMinimumItemPrice(){
        return items.stream()
                .filter(item -> item.getStock() > 0)
                .min(Comparator.comparingDouble(Item::getPrice))
                .map(item -> (int)item.getPrice())
                .orElseThrow(() -> new IllegalArgumentException("유효한 아이템이 없습니다."));
    }

    public Item findItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 상품입니다.");
    }

}
