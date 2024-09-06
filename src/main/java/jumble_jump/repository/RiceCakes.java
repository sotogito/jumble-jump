package jumble_jump.repository;

import jumble_jump.domain.RiceCake;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RiceCakes {
    private final List<RiceCake> riceCakes;

    public RiceCakes(List<RiceCake> riceCakes) {
        this.riceCakes = riceCakes;
    }

    public List<RiceCake> getRiceCakes() {
        return riceCakes;
    }

    public int getMaxRiceCakeLength() {
        return Collections.max(riceCakes, Comparator.comparingInt(RiceCake::getHeight)).getHeight();
    }

}
