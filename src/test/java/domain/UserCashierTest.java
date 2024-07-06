package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.*;

class UserCashierTest {
    private UserCashier userCashier;

    @BeforeEach
    void setUp() {
        userCashier  = new UserCashier(50000); //오만원
    }


    @Test
    @DisplayName("삼품 최소 가격 > 잔돈 = true")
    void isLessThanPurchasedItemAmount(){
        assertTrue(userCashier.isLessThanPurchasedItemAmount(60000));
    }

    @Test
    @DisplayName("잔돈 최소 개수 확인")
    void getChange(){
        userCashier = new UserCashier(50700);

        int expected = 4;
        int actual = userCashier.getChange().values().stream().mapToInt(Integer::intValue).sum();

        assertEquals(expected, actual);
    }



}