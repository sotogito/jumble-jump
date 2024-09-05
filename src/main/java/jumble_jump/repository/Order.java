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

    public List<Integer> getArray() {
        List<Integer> result = new ArrayList<>();
        int maxRiceCakeLength = Collections.max(riceCakes.getRiceCakes(), Comparator.comparingInt(RiceCake::getHeight)).getHeight();
        for(int i = 0; i <= maxRiceCakeLength; i++) {
            result.add(i);
        }

        return result;
    }

    public List<RiceCake> getSortedRiceCakes(){
        List<RiceCake> result = riceCakes.getRiceCakes();
        result.sort(Comparator.comparingInt(RiceCake::getHeight));
        return result;
    }

    public int getRiceCakeLength(){
        return riceCakeLength;
    }

    public int getRiceCakeCount(){
        return riceCakes.getRiceCakeCount();
    }

    public boolean isSameLength(int cutterHeight){
        return riceCakeLength == cutterHeight;
    }

}
