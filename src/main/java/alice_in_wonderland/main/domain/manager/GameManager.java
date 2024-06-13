package alice_in_wonderland.main.domain.manager;

public class GameManager {
    private static int totalCount = 5;

    public int getTotalCount() {
        return totalCount;
    }

    public void decreaseCount(){
        totalCount--;
        System.out.println(totalCount);
    }

    public boolean isGameOver(){
        return totalCount <= 0;
    }


}
