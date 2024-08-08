package jumble_jump.controller;

import jumble_jump.domain.Map;
import jumble_jump.domain.Point;
import jumble_jump.domain.TurnLeftCalculator;
import jumble_jump.domain.UserMoveManager;
import jumble_jump.domain.type.Direction;
import jumble_jump.util.parser.MapLineParser;
import jumble_jump.util.parser.UserStartLocalDataParser;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class GameController {
    /**
     * 1. 맴 크기 입력받기
     * 2. 캐릭터의 좌표와 방향 입력받기
     * 3. n번째 줄 차례대로 입력받기
     * <p>
     * 4. Map, User , Service 생성하기
     */


    public void run() {
        Map map = createMap();
        UserMoveManager userMoveManager = createUserMoveManager(map);

        inputMapType(map, userMoveManager);

        System.out.println(map);
        //시작 위치는 반드시 0이여야함


    }

    public void inputMapType(Map map,UserMoveManager userMoveManager) {
        for (int i = 0; i < map.getMapSize(); i++) {
            while (true) {
                try {
                    List<Integer> mapLine = MapLineParser.parse(Input.inputMapTypesLine(i + 1));
                    //i하고 user위치가 같을때
                    if(userMoveManager.isStartPointYLine(i) && !userMoveManager.isStartPointLandAtInputLine(mapLine)){
                        throw new IllegalArgumentException("시작점 x y는 항상 0이여야합니다.");
                    }
                    map.addMapLine(mapLine);
                    break;
                } catch (IllegalArgumentException e) {
                    Output.printError(e.getMessage());
                }
            }
        }
    }

    public UserMoveManager createUserMoveManager(Map map) {
        while (true) {
            try {
                List<Integer> startDataToken = UserStartLocalDataParser.parse(
                        Input.inputUserStartPointAndDirection(), map);
                Point startPoint = new Point(startDataToken.get(0), startDataToken.get(1));
                Direction startDirection = Direction.fromNumber(startDataToken.get(2));

                return new UserMoveManager(createTurnLeftCalculator(), startPoint, startDirection);
            } catch (IllegalArgumentException e) {
                Output.printError(e.getMessage());
            }
        }
    }

    public TurnLeftCalculator createTurnLeftCalculator() {
        return new TurnLeftCalculator();
    }

    public Map createMap() {
        while (true) {
            try {
                int sizeN = Input.inputMapLengthAndWith();
                return new Map(sizeN);
            } catch (IllegalArgumentException e) {
                Output.printError(e.getMessage());

            }
        }
    }


}
