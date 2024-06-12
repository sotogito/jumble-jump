package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.number.NumberMaker;

public class Door {

    private final NumberMaker numberMaker;
    private final int size;
    private GoOverState goOverState = GoOverState.STAY;

    public Door(NumberMaker numberMaker,int size) {
        this.numberMaker = numberMaker;
        this.size = size;
        //사이즈 생성해야됨
    }

    public boolean getGoOverState() {
        return goOverState.getIsOver();
    }

    public void setGoOverState(GoOverState goOverState) {
        this.goOverState = goOverState;
    }
}
