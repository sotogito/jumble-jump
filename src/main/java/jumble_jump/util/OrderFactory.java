package jumble_jump.util;

import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import jumble_jump.repository.RiceCakes;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderFactory {

    public static Order createOrder(List<RiceCake> riceCakes, int riceCakeLength) {
        validateRiceCakeLength(riceCakes, riceCakeLength);

        return new Order(new RiceCakes(riceCakes),riceCakeLength);
    }

    private static void validateRiceCakeLength(List<RiceCake> riceCakes, int riceCakeLength) {
        int max = Collections.max(riceCakes, Comparator.comparingInt(RiceCake::getHeight)).getHeight();

        if (riceCakeLength < 1 || riceCakeLength > max) {
            throw new IllegalArgumentException("길이는 최소 1에서 쩍길이의 최대값만 가능해요.");
        }
    }
}
