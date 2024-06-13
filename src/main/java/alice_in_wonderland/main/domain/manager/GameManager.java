package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.util.message.GameMessage;

public class GameManager {
    private final static int TOTAL_COUNT = 5;
    private static int counter = TOTAL_COUNT; //static??

    public int getTotalCount() {
        return counter;
    }

    public void decreaseCount() {
        counter--;
    }

    public boolean isOverCount() {
        return counter <= 0;
    }

    public boolean isGameOver(Key key, Door door) {
        return key.getOpenState() && door.getGoOverState();
    }

    public int getAttemptCount() {
        return TOTAL_COUNT - counter;
    }

    @Override
    public String toString() {
        if (isOverCount()) {
            return GameMessage.END_ATTEMPT_COUNT;
        }
        return String.format(GameMessage.REMAINING_COUNTER, counter);
    }

}
