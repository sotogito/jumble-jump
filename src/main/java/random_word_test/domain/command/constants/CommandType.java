package random_word_test.domain.command.constants;

public enum CommandType {
    COMPLETED_INPUT_VOCA("입력 완료"), //InputController

    TEST_ENGLISH("영어"),
    TEST_KOREAN("뜻"),
    QUIT_TEST("그만하기"), //test

    REPLAY("다시 시도"), //시험만 다시
    NEW_VACA_TEST("새로운 단어"), //다시 새로 시작하면 됨 //초기화 어떻게하지
    END("종료"); //TestManager

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand(){
        return String.format(CommandFormatConstant.COMMAND,command);
    }

}
