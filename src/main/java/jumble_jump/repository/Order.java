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
        return Collections.max(riceCakes.getRiceCakes(), Comparator.comparingInt(RiceCake::getHeight)).getHeight();
    }

    public List<RiceCake> getSortedRiceCakes(){
        List<RiceCake> result = riceCakes.getRiceCakes();
        result.sort(Comparator.comparingInt(RiceCake::getHeight));
        return result;
    }

    public int getRiceCakeLength(){
        return riceCakeLength;
    }

}
