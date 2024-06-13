package alice_in_wonderland.main.domain.number;
import java.util.Random;

public class RandomNumberMaker implements NumberMaker {
    Random random = new Random();

    @Override
    public int generate(int min, int max) {
        if (min == max){
            return 1;
        }
        return random.nextInt(max - min) + min;
    }
}
