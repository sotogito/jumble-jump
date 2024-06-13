package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.number.NumberMaker;

public class Door {

    private final NumberMaker numberMaker;
    private final int size;
    private GoOverState goOverState = GoOverState.STAY;

    public Door(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
        size = setDoorSize();
    }

    public boolean getGoOverState() {
        return goOverState.getIsOver();
    }

    public void setGoOverState(GoOverState goOverState,Key key) {
        if (!key.getOpenState()){
            throw new IllegalArgumentException("키로 문 먼저 열어야해!");
        }
        this.goOverState = goOverState;
    }

    private int setDoorSize(){
        int MIN = 2;
        int MAX = 5;
        return numberMaker.generate(MIN,MAX);
    }

    @Override
    public String toString() {
        return "문 : "+size;
    }

}
