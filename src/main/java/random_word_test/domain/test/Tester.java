package random_word_test.domain.test;

public abstract class Tester {
    //NOTE 공통적인 행동
    /**
     * 출력
     * 셔플
     * test 메서드
     */

    private boolean isQuiteTest;

    private final String QUESTION_FORMAT = "%s";

    Tester(){
        isQuiteTest = false;
    }


    public void setQuiteTest(){
        this.isQuiteTest = true;
    }

    public String printTest(String string){
        return String.format(QUESTION_FORMAT, string);
    }

    protected abstract void shuffleList();


    public abstract void test(); //TODO 추상 말고 그냥 부모에서 정의

}
