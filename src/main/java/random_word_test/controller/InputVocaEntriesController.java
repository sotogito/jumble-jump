package random_word_test.controller;

import random_word_test.domain.command.InputVocaEntriesManager;
import random_word_test.domain.voca.VocaPair;
import random_word_test.util.message.ErrorMessage;
import random_word_test.util.validators.CommandFormatValidator;
import random_word_test.view.Input;
import random_word_test.view.Output;

import java.util.ArrayList;
import java.util.List;

public class InputVocaEntriesController {

    private boolean isInputReceived = true;
    private final InputVocaEntriesManager inputVocaEntriesManager;

    InputVocaEntriesController() {
        inputVocaEntriesManager = new InputVocaEntriesManager(this);
    }

    public List<String> getVocaEntries() {
        List<String> vocaEntries = new ArrayList<>();
        while (isInputReceived){
            try{
                String input = Input.inputVocaEntries();

                if(CommandFormatValidator.isContainCommandFormat(input)){
                    inputVocaEntriesManager.inputMatch(input);
                    break;
                }
                vocaEntries.add(input);
            }catch (IllegalArgumentException e){
                if(e.getMessage().contains(ErrorMessage.INVALID_INPUT)){
                    Output.printError(e.getMessage());
                }
            }
        }
        return vocaEntries;
    }

    public void setInputReceivedFalse(){
        this.isInputReceived = false;
    }

}
