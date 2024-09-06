package jumble_jump.repository;

import jumble_jump.domain.RiceCake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Order {
    private final RiceCakes riceCakes;
    private final int riceCakeLength;

    public Order(RiceCakes riceCakes, int riceCakeLength) {
        this.riceCakes = riceCakes;
        this.riceCakeLength = riceCakeLength;
    }

    public int getMaxRiceCakeLength() {
        return riceCakes.getMaxRiceCakeLength();
    }

    public List<RiceCake> getRiceCakes() {
        return riceCakes.getRiceCakes();
    }

    public int getRiceCakeLength() {
        return riceCakeLength;
    }

}
