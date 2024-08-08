package jumble_jump.domain;

import jumble_jump.domain.type.Direction;

import java.util.ArrayList;
import java.util.List;

public class MovePointCalculator {
    private final List<Integer> dx = new ArrayList<>(List.of(-1, 0, 1, 0));
    private final List<Integer> dy = new ArrayList<>(List.of(0, 1, 0, -1));

    public MovePointCalculator() {
    }

    public Direction getNextDirection(Direction nowDirection){
        return Direction.getLeftTurnDirection(nowDirection);
    }

    public int getNextX(int x, int directionIndex){
        return x + dx.get(directionIndex);
    }

    public int getNextY(int y,int directionIndex){
        return y + dy.get(directionIndex);
    }

    public int getBackX(int x, int directionIndex){
        return x - dx.get(directionIndex);
    }

    public int getBackY(int y,int directionIndex){
        return y - dy.get(directionIndex);
    }

}
