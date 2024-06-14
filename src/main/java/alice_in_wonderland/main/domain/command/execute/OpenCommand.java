package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.command.Command;

public class OpenCommand implements Command {
    private final Key key;

    public OpenCommand(Key key) {
        this.key = key;
    }

    @Override
    public void execute() {
        key.changeOpenState();
    }
}
