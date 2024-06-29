package random_word_test.domain.command.execute;

import random_word_test.domain.command.Command;
import random_word_test.domain.test.Tester;

public class InputCommand implements Command {

    private final Tester tester;

    public InputCommand(Tester tester) {
        this.tester = tester;
    }

    @Override
    public void execute() {
        //note 랜덤돌리기? 시험 출력하기?
        //todo
    }
}
