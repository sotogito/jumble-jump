package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.OpenState;
import alice_in_wonderland.main.util.message.GameMessage;

public class Key {
    private final int size = 3;
    private OpenState openState = OpenState.CLOSED;


    //문이 열려있는지 확인한다.
    public boolean getOpenState() {
        return openState.getOpenState();
    }

    public void setOpenState(OpenState openState){
        this.openState = openState;
    }

    public boolean isCanOpen(Alice alice){
        try{
            return alice.isBiggerThanKey(size);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String name = "키";
        return String.format(GameMessage.SIZE, name,size);
    }

}
