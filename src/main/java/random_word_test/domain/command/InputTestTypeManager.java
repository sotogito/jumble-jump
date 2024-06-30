package random_word_test.domain.command;

import random_word_test.domain.TestManager;
import random_word_test.domain.command.constants.CommandType;
import random_word_test.domain.command.execute.InputCommand;
import random_word_test.domain.test.EnglishRandomTester;
import random_word_test.domain.test.KoreanRandomTester;
import random_word_test.domain.test.Tester;
import random_word_test.domain.voca.VocaPair;
import random_word_test.util.message.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class InputTestTypeManager {
    //TODO 그냥 command하고 뭐가 다른거지? 단순히 동작이 아니라 컨트롤러가 필요한거?

    private Command command;
    private final Map<String, Tester> testers = new HashMap<>();
    private TestManager testManager;

    public InputTestTypeManager(String input, VocaPair vocaPair) {
        initTestersMap(vocaPair);
        Tester tester = testers.get(input);

        if (tester == null) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
        testManager = new TestManager(tester);

    }

    public TestManager getTestManager() {
        return testManager;
    }


    private void initTestersMap(VocaPair vocaPair) {
        testers.put(CommandType.TEST_ENGLISH.getCommand(), new EnglishRandomTester(vocaPair));
        testers.put(CommandType.TEST_KOREAN.getCommand(), new KoreanRandomTester(vocaPair));

    }
}
