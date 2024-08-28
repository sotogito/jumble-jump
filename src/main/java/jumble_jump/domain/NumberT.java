package jumble_jump.domain;

public class NumberT implements Token{
    private final int number;

    public NumberT(int number) {
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
