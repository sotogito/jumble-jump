package domain.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {
    private Items items;

    @BeforeEach
    void setUp() {
        Item item1 = new Item("사과",1000,2);
        Item item2 = new Item("참외",100,5);
        Item item3 = new Item("수키피",10000000,1);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        items = new Items(itemList);
    }

    @Test
    @DisplayName("가장 작은 가격 반환하기")
    void getMinimumItemPrice(){
        int expected = 100;
        long  actual= items.getMinimumPriceItem().getPrice();

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("아이템 이름으로 찾아오기")
    void findItemByName(){
        String expected = "사과";

        Item foundItem = items.findItemByName(expected);
        String  actual= foundItem.getName();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("가장 작은 가격 반환하기 - 수량 0인경우 제외")
    void getMinimumItemPrice_Exception_outofstock(){
        Item foundItem = items.findItemByName("참외");
        foundItem.decreaseStock(5);

        System.out.println(foundItem.getStock());


        int expected = 1000;
        long  actual= items.getMinimumPriceItem().getPrice();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("존재하지 않는 상품")
    void findItemByName_no_exist() {
        String input = "라이즈";
        assertThatThrownBy(() -> items.findItemByName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }



}