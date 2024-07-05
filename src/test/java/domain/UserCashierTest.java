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

    /*
    @Test
    @DisplayName("아이템 가격 < 사용자 잔돈 => 잔돈 정상적 차감")
    void decreaseAmountAsPurchased_success(){
        userCashier.decreaseAmountAsPurchased(40000); //사만원

        long expected = 10000;
        long actual = userCashier.getAmount();

        assertEquals(expected, actual);
    }

     */
    @Test
    @DisplayName("아이템 가격 > 사용자 잔돈 => 예외, 숫자 포맷 확인")
    void decreaseAmountAsPurchased_fail(){
        String expected = "10,000원이 부족해요.";

        assertThatThrownBy(() -> userCashier.decreaseAmountAsPurchased(60000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expected);
    }

    @Test
    @DisplayName("삼품 최소 가격 > 잔돈 = true")
    void isLessThanPurchasedItemAmount(){
        assertTrue(userCashier.isLessThanPurchasedItemAmount(60000));
    }



}