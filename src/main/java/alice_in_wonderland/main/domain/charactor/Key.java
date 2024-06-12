package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.OpenState;

public class Key {
    private final int size = 3;
    private OpenState openState = OpenState.CLOSED;

    public int getSize(){
        return size;
    }

    //문이 열려있는지 확인한다.
    public boolean getOpenState() {
        return openState.getOpenState();
    }

    public void setOpenState(OpenState openState){
        this.openState = openState;
    }

}
