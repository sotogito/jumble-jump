package domain;

import domain.item.Item;
import domain.item.Items;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 잔돈 < 최소금액
 * 잔돈 > 최소금액
 *
 * 잔돈 < 구매금액 && 수량 > 구매수량
 * 잔돈 > 구매금액 && 수랭 < 구매수량
 *
 * 잔돈 > 구매수량 && 수량 > 구매수량
 */

class StoreTest {
    private Store store;



    @Test
    @DisplayName("잔돈 < 최소금액")
    void isCanPurchase_false_fail(){
        Item item1 = new Item("사과",1000,3);
        Item item2 = new Item("참외",2000,5);

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);

        Items items = new Items(itemsList);
        UserCashier userCashier = new UserCashier(2100);

        store = new Store(items, userCashier);

        assertFalse(store.isCanPurchase("사과",2));

    }

    @Test
    @DisplayName("잔돈 > 최소금액")
    void isCanPurchase_true_success(){
        Item item1 = new Item("사과",1000,3);
        Item item2 = new Item("참외",2000,5);

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);

        Items items = new Items(itemsList);
        UserCashier userCashier = new UserCashier(6100);
        store = new Store(items, userCashier);
        store.isCanPurchase("참외",1);

        assertTrue(store.isCanPurchase("사과",2));

    }

    @Test
    @DisplayName("잔돈 < 구매금액 && 수량 > 구매수량")
    void isCanPurchase_Exception_amount_fail(){
        Item item1 = new Item("사과",1000,3);
        Item item2 = new Item("참외",2000,5);

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);

        Items items = new Items(itemsList);
        UserCashier userCashier = new UserCashier(7100);
        store = new Store(items, userCashier);

        String expected = "900원이 부족해요.";


        assertThatThrownBy(() -> store.isCanPurchase("참외",4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expected);

        //참외 재고
        //사용자 잔액 그대로인지 확인
        assertEquals(5, item2.getStock());
    }

    @Test
    @DisplayName("잔돈 > 구매금액 && 수랭 < 구매수량")
    void isCanPurchase_Exception_stock_fail(){
        Item item1 = new Item("사과",1000,3);
        Item item2 = new Item("참외",2000,5);

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);

        Items items = new Items(itemsList);
        UserCashier userCashier = new UserCashier(7100);
        store = new Store(items, userCashier);

        String expected = "사과는(은) 수량이 3개 뿐이에요!";


        assertThatThrownBy(() -> store.isCanPurchase("사과",7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expected);

        assertEquals(3, item1.getStock());
    }

    @Test
    @DisplayName("잔돈 > 구매수량 && 수량 > 구매수량")
    void isCanPurchase_success(){
        Item item1 = new Item("사과",1000,3);
        Item item2 = new Item("참외",2000,5);

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);

        Items items = new Items(itemsList);
        UserCashier userCashier = new UserCashier(100100);
        store = new Store(items, userCashier);

        //store.isCanPurchase("사과",3);

        assertTrue(store.isCanPurchase("사과",3));
        assertTrue(store.isCanPurchase("참외",3));

    }

    @Test
    @DisplayName("모든 상품 품절")
    void all_stock_zero(){
        Item item1 = new Item("사과",1000,1);
        Item item2 = new Item("참외",100,1);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        Items items = new Items(itemList);

        UserCashier userCashier = new UserCashier(100100);
        store = new Store(items, userCashier);

        store.isCanPurchase("사과",1);

        String expectedMessage = "모든 상품이 소진되었어요. 장사 끝!";

        assertFalse(store.isCanPurchase("참외",1),expectedMessage );

    }



}