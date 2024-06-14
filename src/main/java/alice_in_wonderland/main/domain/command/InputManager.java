package alice_in_wonderland.main.domain.command;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.command.execute.DrinkCommand;
import alice_in_wonderland.main.domain.command.execute.GoOverCommand;
import alice_in_wonderland.main.domain.command.execute.MushroomCommand;
import alice_in_wonderland.main.domain.command.execute.OpenCommand;
import alice_in_wonderland.main.util.message.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class InputManager {
    //입력된 String을 Map의 데이터와 비교하여 해당 객체의 command를 실행한다.

    private final Map<String, Command> commands = new HashMap<>();

    public InputManager(Alice alice, Door door, Key key) {
        initCommandsMap(alice, door, key);
    }

    public void registerCommand(String input) {
        Command command = commands.get(input);
        if (command != null) {
            command.execute();
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
    }

    public void initCommandsMap(Alice alice, Door door, Key key){
        commands.put("음료", new DrinkCommand(alice));
        commands.put("버섯", new MushroomCommand(alice));
        commands.put("open", new OpenCommand(key , alice));
        commands.put("go over", new GoOverCommand(door, key,alice));
    }


}
