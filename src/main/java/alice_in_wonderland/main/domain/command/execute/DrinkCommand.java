package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.command.Command;
import alice_in_wonderland.main.domain.manager.GameManager;

public class DrinkCommand implements Command {
    private final Alice alice;

    public DrinkCommand(Alice alice) {
        this.alice = alice;
    }

    @Override
    public void execute(){
        alice.growSize();
    }
}
