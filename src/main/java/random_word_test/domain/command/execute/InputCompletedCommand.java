package random_word_test.domain.command.execute;

import random_word_test.controller.InputVocaEntriesController;
import random_word_test.domain.command.Command;

public class InputCompletedCommand implements Command {

    private final InputVocaEntriesController inputController;

    public InputCompletedCommand(InputVocaEntriesController inputController) {
        this.inputController = inputController;
    }

    @Override
    public void execute(){
        inputController.setInputReceivedFalse();
    }
}
