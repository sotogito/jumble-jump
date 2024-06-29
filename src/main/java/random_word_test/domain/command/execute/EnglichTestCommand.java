package random_word_test.domain.command.execute;

import random_word_test.domain.command.Command;
import random_word_test.domain.test.EnglishRandomTester;

public class EnglichTestCommand implements Command {

    private final EnglishRandomTester englishRandomTester;

    public EnglichTestCommand(EnglishRandomTester englishRandomTester) {
        this.englishRandomTester = englishRandomTester;
    }


    @Override
    public void execute(){
        //todo 영어시험 출력

    }
}
