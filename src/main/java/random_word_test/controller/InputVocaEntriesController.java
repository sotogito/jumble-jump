package random_word_test.controller;

import random_word_test.domain.voca.VocaPair;
import random_word_test.view.Input;

import java.util.ArrayList;
import java.util.List;

public class InputVocaEntriesController {

    private boolean isInputReceived = true;

    public List<String> getVocaEntries() {
        List<String> vocaEntries = new ArrayList<>();

        while (!isInputReceived){
            String input = Input.inputVocaEntries();
            vocaEntries.add(input);
        }
        return vocaEntries;
    }


    public void setInputReceivedFalse(){
        this.isInputReceived = false;
    }

}
