package random_word_test.controller;

import random_word_test.view.Input;

import java.util.ArrayList;
import java.util.List;

public class VocaTest {

    public void test(){
        List<String> vocaEntries = getVocaEntries();

        for (String vocaEntry : vocaEntries) {
            System.out.println(vocaEntry);
        }

    }

    public List<String> getVocaEntries() {
        List<String> vocaEntries = new ArrayList<>();
        while (true){
            String input = Input.inputVocaEntries();
            vocaEntries.add(input);
            if (input.equals("입력 완료")) {
                break;
            }
        }
        return vocaEntries;
    }



}
