package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.command.Command;
import alice_in_wonderland.main.util.message.ErrorMessage;

public class OpenCommand implements Command {
    private final Key key;
    private final Alice alice;

    public OpenCommand(Key key,Alice alice) {
        this.key = key;
        this.alice = alice;
    }

    @Override
    public void execute() {
        if(alice.aliceIsBiggerThanKey(key.getSize())){
            key.changeOpenState();
        }
        throw new IllegalArgumentException(ErrorMessage.CANT_OPEN_WITH_KEY);
    }
}
