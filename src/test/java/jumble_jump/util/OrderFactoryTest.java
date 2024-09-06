package jumble_jump.util;

import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void ORDER_객체_생성_성공(){
        List<RiceCake> riceCakeList = new ArrayList<>();
        riceCakeList.add(new RiceCake(1));
        riceCakeList.add(new RiceCake(15));
        riceCakeList.add(new RiceCake(19));

        int riceCakeLength = 6;

        boolean expected = true;
        boolean actual = OrderFactory.createOrder(riceCakeList,riceCakeLength) instanceof Order;

        assertEquals(expected, actual);
    }

    @Test
    void 떡의_최대값을_넘겼을_때_예외처리(){
        List<RiceCake> riceCakeList = new ArrayList<>();
        riceCakeList.add(new RiceCake(1));
        riceCakeList.add(new RiceCake(15));
        riceCakeList.add(new RiceCake(19));

        int riceCakeLength = 40;

        assertThatThrownBy(() -> OrderFactory.createOrder(riceCakeList,riceCakeLength))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("길이는 최소 1에서 입력된 떡 길이의 최대값만 가능해요.");
    }

}