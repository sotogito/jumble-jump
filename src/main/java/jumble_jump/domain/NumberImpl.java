package jumble_jump.domain;

import jumble_jump.domain.tokens.NumberT;
import jumble_jump.domain.type.GameElement;

public class NumberImpl implements NumberT {
    private final int number;

    public NumberImpl(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public GameElement getGameElement() {
        return GameElement.NUMBER;
    }
}
