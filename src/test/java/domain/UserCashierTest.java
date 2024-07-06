package domain;

import domain.item.Item;
import domain.item.Items;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.*;

class UserCashierTest {
    private UserCashier userCashier;
    private Items items;

    @BeforeEach
    void setUp() {
        Item item1 = new Item("치킨",100,1);
        Item item2 = new Item("엽떡",100,1);
        List<Item> itemList = List.of(item1, item2);

        items = new Items(itemList);
        userCashier  = new UserCashier(50000,items); //오만원
    }


    @Test
    @DisplayName("삼품 최소 가격 > 잔돈 = true")
    void isLessThanPurchasedItemAmount(){
        assertTrue(userCashier.isLessThanPurchasedItemAmount(60000));
    }

    @Test
    @DisplayName("잔돈 최소 개수 확인")
    void getChange(){
        userCashier = new UserCashier(50700,items);

        int expected = 4;
        int actual = userCashier.getChange().values().stream().mapToInt(Integer::intValue).sum();

        assertEquals(expected, actual);
    }



}