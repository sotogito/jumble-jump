package random_word_test.domain;

import random_word_test.domain.test.Tester;

public class TestManager {

    private boolean isProgramEnd = false;
    private Tester tester; //영어시험이나 뜻시험, 조정 가능


    public TestManager(Tester tester) {
        this.tester = tester;
    }

    public void startTest(){
        tester.test();
    }

    public void setFinishTest(){ //todo 테스트 종료
        tester.setQuiteTest();
    }

    public void setFinishProgram(){
        isProgramEnd = true;
    }

    public boolean isProgramEnd() { //todo 아예 프로그램 종료
        return isProgramEnd;
    }



}
