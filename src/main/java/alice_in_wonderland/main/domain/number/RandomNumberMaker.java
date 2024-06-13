package alice_in_wonderland.main.domain.number;

import java.util.Random;

public class RandomNumberMaker implements NumberMaker {

    private final int MIN_GROW_SIZE = 1;
    Random random = new Random();

    @Override
    public int generate(int min, int max) {
        if (min == max) {
            return MIN_GROW_SIZE;
        }
        return random.nextInt(max - min) + min;
    }

}
