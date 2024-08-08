package jumble_jump.controller;

import jumble_jump.domain.Map;
import jumble_jump.domain.Point;
import jumble_jump.domain.TurnLeftCalculator;
import jumble_jump.domain.UserMoveManager;
import jumble_jump.domain.type.Direction;
import jumble_jump.service.GameServer;
import jumble_jump.util.parser.MapLineParser;
import jumble_jump.util.parser.UserStartLocalDataParser;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class GameController {

    public GameServer gameServer;

    public void run() {
        Map map = createMap();
        UserMoveManager userMoveManager = createUserMoveManager(map);

        inputMapType(map, userMoveManager);

        createGameServer(map, userMoveManager);

        gameServer.run();

        System.out.println(userMoveManager.getMoveCount());


    }

    public void createGameServer(Map map,UserMoveManager userMoveManager){
        gameServer = new GameServer(map, userMoveManager);
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
