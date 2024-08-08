package jumble_jump.domain;

import jumble_jump.domain.type.Direction;

import java.util.ArrayList;
import java.util.List;

public class UserMoveManager {
    private TurnLeftCalculator turnLeftCalculator;

    private List<Point> moved = new ArrayList<>();
    private Point currentPoint;
    private Direction currentDirection;

    private int moveCount = 0;

    public UserMoveManager(TurnLeftCalculator turnLeftCalculator, Point currentPoint, Direction currentDirection) {
        this.turnLeftCalculator = turnLeftCalculator;
        this.currentPoint = currentPoint;
        this.currentDirection = currentDirection;
    }

    /**
     * moved에 들어있는 좌표인지
     * currentPoint 업데이드
     * currentDirection 업데이트
     */

    public void incrementMoveCount() {
        moveCount++;
    }


    public void addMovedPoint() {
        moved.add(currentPoint);
    }

    public void setTurnLeftDirection(){
        currentDirection = turnLeftCalculator.getNextDirection(currentDirection);
    }

    public void setCurrentPoint(Point nextPoint){
        //currentPoint.setX(nextPoint.getX());
       // currentPoint.setY(nextPoint.getY()); //fixme 이렇게하면 moved.add(currentPoint);의 값도 바뀔거같다. 같은 인스턴스
        currentPoint = nextPoint;
    }

    public Point getLeftPoint() {
        int x = currentPoint.getX();
        int y = currentPoint.getY();
        int directionIndex = currentDirection.getNumber();

        int nx = turnLeftCalculator.getNextX(x,directionIndex);
        int ny = turnLeftCalculator.getNextY(y,directionIndex);

        return new Point(nx,ny); //note 새로 값을 반환해야 moved에 넣을때 다 다른 객체를 넣을 수 있음
    }

    public Point getBackPoint(){
        int x = currentPoint.getX();
        int y = currentPoint.getY();
        int directionIndex = currentDirection.getNumber();

        int bx = turnLeftCalculator.getBackX(x,directionIndex);
        int by = turnLeftCalculator.getBackY(y,directionIndex);

        return new Point(bx,by);
    }

    public boolean isMovedPoint(Point point) {
        return moved.contains(point);
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }




}
