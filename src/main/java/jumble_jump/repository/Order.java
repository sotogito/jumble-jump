package jumble_jump.repository;

import jumble_jump.domain.RiceCake;

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

    public List<RiceCake> getSortedRiceCakes() {
        List<RiceCake> riceCakeList = riceCakes.getRiceCakes();
        riceCakeList.sort(Comparator.comparingInt(RiceCake::getHeight));
        return riceCakeList;
    }

    public boolean isSameLength(int cutterHeight){
        return riceCakeLength == cutterHeight;
    }

}
