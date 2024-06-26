package random_word_test.domain.test;

public class RandomTestPrinter implements Tester {

    @Override
    public void test(String question){
        System.out.println(question);
    }
}
