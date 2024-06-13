package alice_in_wonderland.main.domain.charactor;

import alice_in_wonderland.main.domain.gamedata.GoOverState;
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

    public boolean getGoOverState() {
        return goOverState.getIsOver();
    }

    public boolean isAlreadyOpenDoor(Key key){
        if (!key.getOpenState()){
            throw new IllegalArgumentException(ErrorMessage.CANT_GO_OVER_NOT_YET_OPEN); //키가 던져야하는 메시지인가?
        }
        return true;
    }

    public boolean isCanGoOver(Alice alice){
        try{
            return alice.isSmallerThanDoor(size);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void setGoOverState(GoOverState goOverState) {
        this.goOverState = goOverState;
    }

    private int setDoorSize(){
        int MIN = 2;
        int MAX = 5;
        return numberMaker.generate(MIN,MAX);
    }

    @Override
    public String toString() {
        String name = "문";
        return String.format(GameMessage.SIZE, name,size);
    }

}
