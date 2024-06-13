package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;

public class GameManager {
    private static int totalCount = 5;

    public int getTotalCount() {
        return totalCount;
    }

    public void decreaseCount(){
        totalCount--;
        System.out.println(totalCount);
    }

    public boolean isOverCount(){
        return totalCount <= 0;
    }

    public boolean isGameOver(Key key, Door door){
        return key.getOpenState() &&door.getGoOverState();
    }




}
