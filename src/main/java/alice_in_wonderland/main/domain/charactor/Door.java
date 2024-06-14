package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.states.GoOverState;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import alice_in_wonderland.main.util.message.GameMessage;

public class Door {
    private final NumberMaker numberMaker;
    private final int size;
    private GoOverState goOverState = GoOverState.STAY;


    public Door(NumberMaker numberMaker) {
        this.numberMaker = numberMaker;
        size = setDoorSize();
    }

    public void changeGoOverState() {
        goOverState = GoOverState.GO_OVER;
    }

    public int getSize(){
        return size;
    }







    public boolean getGoOverState() {
        return goOverState.getIsOver();
    }


    private int setDoorSize() {
        int MIN = 2;
        int MAX = 5;
        return numberMaker.generate(MIN, MAX);
    }

    @Override
    public String toString() {
        String name = "문";
        return String.format(GameMessage.SIZE, name, size);
    }

}
