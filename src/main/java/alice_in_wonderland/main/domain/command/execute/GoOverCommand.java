package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.command.Command;

public class GoOverCommand implements Command {
    private final Door door;

    public GoOverCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.changeGoOverState();
    }

}
