package jumble_jump.controller;

import jumble_jump.domain.Map;
import jumble_jump.domain.Point;
import jumble_jump.domain.TurnLeftCalculator;
import jumble_jump.domain.UserMoveManager;
import jumble_jump.domain.dto.StartPositionDTO;
import jumble_jump.domain.type.Direction;
import jumble_jump.service.GameServer;
import jumble_jump.util.MapValidator;
import jumble_jump.util.parser.MapLineParser;
import jumble_jump.util.parser.UserInputStartPositionParser;
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

        Output.printMap(map);
        Output.printMoveCountResult(userMoveManager);
    }

    public void createGameServer(Map map,UserMoveManager userMoveManager){
        gameServer = new GameServer(map, userMoveManager);
    }

    public void inputMapType(Map map,UserMoveManager userMoveManager) {
        for (int i = 0; i < map.getMapSize(); i++) {
            while (true) {
                try {
                    List<Integer> mapLine = MapLineParser.parse(Input.inputMapTypesLine(i + 1));
                    MapValidator.validateStartPointInInputLine(i,mapLine,userMoveManager);
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
                StartPositionDTO dto = getStartPositionDTO(map);
                return new UserMoveManager(createTurnLeftCalculator(), dto.getStartPosition(), dto.getDirection());
            } catch (IllegalArgumentException e) {
                Output.printError(e.getMessage());
            }
        }
    }

    public StartPositionDTO getStartPositionDTO(Map map){
        return UserInputStartPositionParser.parse(Input.inputUserStartPointAndDirection(), map);
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
