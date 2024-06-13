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

    public boolean isAlreadyOpenDoor(Key key){
        if (!key.getOpenState()){
            throw new IllegalArgumentException("키로 문 먼저 열어야해!"); //키가 던져야하는 메시지인가?
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
        return "문의 크기 : "+size;
    }

}
