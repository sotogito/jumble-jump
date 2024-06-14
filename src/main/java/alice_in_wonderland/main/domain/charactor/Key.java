package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.states.OpenState;
import alice_in_wonderland.main.util.message.GameMessage;

public class Key {
    private final int size = 3;
    private OpenState openState = OpenState.CLOSED;

    public void changeOpenState() {

        openState = OpenState.OPEN;
    }

    public int getSize(){
        return size;
    }





    public boolean getOpenState() {
        return openState.getOpenState();
    }



    @Override
    public String toString() {
        String name = "키";
        return String.format(GameMessage.SIZE, name, size);
    }

}
