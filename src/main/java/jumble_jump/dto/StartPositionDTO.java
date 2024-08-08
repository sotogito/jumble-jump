package jumble_jump.dto;

import jumble_jump.domain.Point;
import jumble_jump.domain.type.Direction;

/**
 *  [x y 방향] 형태로 입력받은 데이터를 파싱한 후, 이를 UserMoveManager에 저장하기 전에 사용하는 중간 데이터 전달 객체(Data Transfer Object, DTO)의 역할
 */
public class StartPositionDTO {
    private final Point startPosition;
    private final Direction direction;

    public StartPositionDTO(Point startPosition, Direction direction) {

        this.startPosition = startPosition;
        this.direction = direction;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public Direction getDirection() {
        return direction;
    }

}
