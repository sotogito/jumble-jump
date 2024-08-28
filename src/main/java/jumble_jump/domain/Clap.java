package jumble_jump.domain;

public class Clap implements Token{
    private final String clap = "짝";
    private final int count;

    public Clap(int count) {
        this.count = count;
    }

    public int getClap() {
        return count;
    }

}
