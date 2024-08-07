package jumble_jump.service;

import jumble_jump.domain.Map;
import jumble_jump.domain.Point;
import jumble_jump.domain.UserMoveManager;

public class GameServer {
    private final Map map;
    private final UserMoveManager userMoveManager;

    private int blockMoveCounter = 0;

    public GameServer(Map map, UserMoveManager userMoveManager) {
        this.map = map;
        this.userMoveManager = userMoveManager;
    }

    public void run(){
        while (true) {
            userMoveManager.setTurnLeftDirection();
            Point nextPoint = userMoveManager.getLeftPoint();

            if(isCanMoveToLeftPoint(nextPoint)) {
                userMoveManager.addMovedPoint();
                userMoveManager.setCurrentPoint(nextPoint);
                userMoveManager.incrementMoveCount();
                initBlockMoveCounter();
                continue;
            }
            blockMoveCounter ++;

            if(blockMoveCounter == 4){
                Point backPoint = userMoveManager.getBackPoint();

                if(isCanMoveToBackPoint(backPoint)) {
                    userMoveManager.setCurrentPoint(backPoint);
                    continue;
                }
                break;
            }
        }
    }

    public boolean isCanMoveToLeftPoint(Point nextPoint) {
        return !userMoveManager.isMovedPoint(nextPoint)
                && map.isCanMoveLand(nextPoint)
                && map.isWithinMap(nextPoint);
    }

    public boolean isCanMoveToBackPoint(Point backPoint) {
        return map.isCanMoveLand(backPoint)
                && map.isWithinMap(backPoint);
    }



    public void initBlockMoveCounter() {
        blockMoveCounter = 0;
    }

}
