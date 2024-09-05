package jumble_jump.domain;

import java.util.List;

public class RiceCakes {
    private final List<RiceCake> riceCakes;

    public RiceCakes(List<RiceCake> riceCakes) {
        this.riceCakes = riceCakes;
    }

    public List<RiceCake> getRiceCakes() {
        return riceCakes;
    }

}
