package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;

public class GameManager {
    private final static int TOTAL_COUNT = 5;
    private static int counter = TOTAL_COUNT; //static??
    //private final int

    public int getTotalCount() {
        return counter;
    }

    public void decreaseCount(){
        counter--;
    }

    public boolean isOverCount(){
        return counter <= 0;
    }

    public boolean isGameOver(Key key, Door door){
        return key.getOpenState() &&door.getGoOverState();
    }

    public int getAttemptCount(){
        return TOTAL_COUNT - counter;
    }

    @Override
    public String toString() {
        if (isOverCount()){
            return "시도 횟수가 끝났다!!";
        }
        return String.format("시도 횟수는 %d번 남았습니다.", counter);
    }




}
