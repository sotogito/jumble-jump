package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.GoOverState;

public class Door {

    private final int size;
    private GoOverState goOverState = GoOverState.STAY;

    public Door(int size) {
        this.size = size;
        //사이즈 생성해야됨
    }

    public boolean getGoOverState() {
        return goOverState.getIsOver();
    }
}
