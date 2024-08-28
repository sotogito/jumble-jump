package jumble_jump.domain;

public class ClapImpl implements Token{
    private final String clap = "짝";
    private final int count;

    public ClapImpl(int count) {
        this.count = count;
    }

    public int getClap() {
        return count;
    }

    @Override
    public GameElement getGameElement() {
        return GameElement.CLAP;
    }

}
