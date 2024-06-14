package alice_in_wonderland.main.domain.command.execute;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.command.command;

public class DrinkCommand implements command {
    private final Alice alice;

    public DrinkCommand(Alice alice) {
        this.alice = alice;
    }

    @Override
    public void execute(){
        alice.growSize();
    }
}
