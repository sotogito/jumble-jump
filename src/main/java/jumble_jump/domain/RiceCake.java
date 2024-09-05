package jumble_jump.domain;

public class RiceCake {
    private final int height;

    public RiceCake(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getHeightOfCuttingWithCutter(int cutterHeight){
        return height - cutterHeight;
    }

    public boolean canCut(int cutterHeight) {
        return height > cutterHeight;
    }

}
