package random_word_test.domain.command.execute;

import random_word_test.domain.TestManager;
import random_word_test.domain.command.Command;

public class EndCommand implements Command {

    private final TestManager testManager;

    public EndCommand(TestManager testManager) {
        this.testManager = testManager;
    }


    @Override
    public void execute(){
        testManager.setTestEnded();
    }
}
