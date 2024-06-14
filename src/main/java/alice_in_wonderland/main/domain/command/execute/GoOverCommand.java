package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.command.Command;
import alice_in_wonderland.main.util.message.ErrorMessage;

public class GoOverCommand implements Command {
    private final Door door;
    private final Key key;

    public GoOverCommand(Door door, Key key) {
        this.door = door;
        this.key = key;
    }

    @Override
    public void execute() {
        if(key.getOpenState()){
            door.changeGoOverState();
        }
        throw new IllegalArgumentException(ErrorMessage.CANT_GO_OVER_NOT_YET_OPEN);
    }

}
