package random_word_test.domain.test;

public abstract class Tester {

    private final String QUESTION_FORMAT = "%s";

    public String test(String string){
        return String.format(QUESTION_FORMAT, string);
    }

}
