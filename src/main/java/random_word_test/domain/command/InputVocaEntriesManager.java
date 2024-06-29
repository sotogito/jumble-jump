package random_word_test.domain.command;

import random_word_test.controller.InputVocaEntriesController;
import random_word_test.domain.command.constants.CommandType;
import random_word_test.domain.command.execute.InputCompletedCommand;
import random_word_test.util.message.ErrorMessage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InputVocaEntriesManager {
    //TODO 입력완료

    private final Map<String, Command> commandMap = new HashMap<>();

    public InputVocaEntriesManager(InputVocaEntriesController inputController) {
        initCommandMap(inputController);
    }

    public void inputMatch(String input){
        Command command = commandMap.get(input);

        if(command == null){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
        command.execute();
    }

    private void initCommandMap(InputVocaEntriesController inputController){
        commandMap.put(CommandType.COMPLETED_INPUT_VOCA.getCommand(), new InputCompletedCommand(inputController));
    }



}
