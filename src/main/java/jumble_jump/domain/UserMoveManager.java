package jumble_jump.domain;

import jumble_jump.domain.type.Direction;
import jumble_jump.domain.type.MapType;

import java.util.ArrayList;
import java.util.List;

public class UserMoveManager {
    private final MovePointCalculator movePointCalculator;

    private final List<Point> moved = new ArrayList<>(); //note currentPoint객체가 저장됨,currentPoint도 항상 다르기 때문에 moved도 다름

    //private Point currentPoint; //note 항상 새로운 객체가 저장됨
    //private Direction currentDirection;

    private final Position currentPosition;

    private int moveCount = 1;

    public UserMoveManager(MovePointCalculator turnLeftCalculator, Point currentPoint, Direction currentDirection) {
        this.movePointCalculator = turnLeftCalculator;
        //this.currentPoint = currentPoint;
        //this.currentDirection = currentDirection;
        this.currentPosition = new Position(currentPoint, currentDirection);
    }

    public void incrementMoveCount() {
        moveCount++;
    }

    public void addMovedPoint() {
        //moved.add(currentPoint);
        moved.add(currentPosition.getPoint());
    }

    public void setTurnLeftDirection(){
        //currentDirection = movePointCalculator.getNextDirection(currentDirection);
        currentPosition.setDirection(movePointCalculator.getNextDirection(currentPosition.getDirection()));
    }

    public void setCurrentPoint(Point nextPoint){
        //currentPoint = nextPoint;
        currentPosition.setPoint(nextPoint);
    }

    public Point getLeftPoint() {
        //int x = currentPoint.getX();
        //int y = currentPoint.getY();
        //int directionIndex = currentDirection.getNumber();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        int directionIndex = currentPosition.getDirectionNumber();

        int nx = movePointCalculator.getNextX(x,directionIndex);
        int ny = movePointCalculator.getNextY(y,directionIndex);

        return new Point(nx,ny); //note 새로 값을 반환해야 moved에 넣을때 각자 다른 객체를 넣을 수 있음
    }

    public Point getBackPoint(){
        //int x = currentPoint.getX();
        //int y = currentPoint.getY();
        //int directionIndex = currentDirection.getNumber();

        int x = currentPosition.getX();
        int y = currentPosition.getY();
        int directionIndex = currentPosition.getDirectionNumber();

        int bx = movePointCalculator.getBackX(x,directionIndex);
        int by = movePointCalculator.getBackY(y,directionIndex);

        return new Point(bx,by);
    }

    public boolean isMovedPoint(Point point) {
        return moved.contains(point);
    }

    public boolean isStartPointLandAtInputLine(List<Integer> startPointInputMapLine){
        int x = startPointInputMapLine.get(currentPosition.getX());
        return x == MapType.LAND.getValue();
    }

    public boolean isStartPointYLine(int inputOrder){
        return inputOrder == currentPosition.getY();
    }

    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(currentPosition.getX()).append("\n");
        str.append(currentPosition.getY()).append("\n");
        str.append(currentPosition.getDirectionNumber()).append("\n");

        return str.toString();
    }

}
