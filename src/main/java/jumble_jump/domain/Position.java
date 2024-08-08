package jumble_jump.domain;

import jumble_jump.domain.type.Direction;

public class Position {
    private Point point;
    private Direction direction;

    public Position(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDirectionNumber() {
        return direction.getNumber();
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public void setPoint(Point nextPoint) {
        point = nextPoint;
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

}
