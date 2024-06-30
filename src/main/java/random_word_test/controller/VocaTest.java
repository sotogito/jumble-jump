package random_word_test.controller;

import random_word_test.domain.TestManager;
import random_word_test.domain.command.InputTestTypeManager;
import random_word_test.domain.command.constants.CommandType;
import random_word_test.domain.voca.VocaPair;
import random_word_test.view.Input;
import random_word_test.view.Output;

import java.util.ArrayList;
import java.util.List;

public class VocaTest {

    public void test(){
        VocaPair vocaPair = createVocaPair();
        vocaPair.printVocabulary();

        //[입력 완료]만 따로 입력받기
        //영어가아니거나. 한국어가 아니거나, 특수기호가 들어가면 아예 처음부터 다시 입력받기

        InputTestTypeManager inputTestTypeManager = new InputTestTypeManager(Input.inputVocaEntries(),vocaPair);

        TestManager testManager = inputTestTypeManager.getTestManager();

        testManager.startTest(); //출력돼야됨 각 오버라이드되어있는

    }


    public VocaPair createVocaPair(){
        Output.printInputVoca();
        try{
            return new VocaPair(getVocaEntries());
        }catch (IllegalArgumentException e){
            Output.printError(e.getMessage());
        }
        return createVocaPair();
    }

    public List<String> getVocaEntries() {
        InputVocaEntriesController inputVocaController = new InputVocaEntriesController();
        return inputVocaController.getVocaEntries();
    }



}
