package random_word_test.domain.command.execute;

import random_word_test.domain.command.Command;
import random_word_test.domain.test.KoreanRandomTester;

public class KoreanTestCommand implements Command {

    private final KoreanRandomTester koreanRandomTester;

    public KoreanTestCommand(KoreanRandomTester koreanRandomTester) {
        this.koreanRandomTester = koreanRandomTester;
    }

    @Override
    public void execute(){

        //todo 한글 시험보기

    }
}
