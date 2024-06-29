package random_word_test.domain.command;

public enum CommandType {
    COMPLETED_INPUT_VOCA("입력 완료"),

    TEST_ENGLISH("영어"),
    TEST_KOREAN("뜻"),
    QUIT_TEST("그만한기"),

    REPLAY("다시 시도"),
    NEW_VACA_TEST("새로운 단어"),
    END("종료");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand(){
        return String.format(CommandFormatConstant.COMMAND,command);
    }

}
