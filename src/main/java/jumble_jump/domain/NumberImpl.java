package jumble_jump.domain;

public class NumberImpl implements NumberT{
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
