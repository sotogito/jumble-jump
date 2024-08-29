package jumble_jump.domain;

import jumble_jump.domain.tokens.Clap;
import jumble_jump.domain.type.GameElement;

public class ClapImpl implements Clap {
    private final int count;

    public ClapImpl(int count) {
        this.count = count;
    }

    @Override
    public int getClap() {
        return count;
    }

    @Override
    public GameElement getGameElement() {
        return GameElement.CLAP;
    }

}
