package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.states.OpenState;
import alice_in_wonderland.main.util.message.GameMessage;

public class Key {
    private final int size = 3;
    private OpenState openState = OpenState.CLOSED;


    public boolean getOpenState() {
        return openState.getOpenState();
    }

    public void setOpenState(OpenState openState) {
        this.openState = openState;
    }

    public boolean isCanOpen(Alice alice) {
        return alice.isBiggerThanKey(size);
    }

    @Override
    public String toString() {
        String name = "키";
        return String.format(GameMessage.SIZE, name, size);
    }

}
