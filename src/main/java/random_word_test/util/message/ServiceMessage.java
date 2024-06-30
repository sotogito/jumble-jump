package random_word_test.util.message;

public enum ServiceMessage {
    INPUT_VOCA("영어단어와 뜻을 입력해주세요"),
    INPUT_TEST_TYPE("영어 단어와 뜻 중 무엇을 시험해볼까요?"),

    INTRO_PRINT_TEST("지금부터 순서를 랜덤으로 한 %d문제를 출력할게요"),

    INTRO_INTRO("성적표를 출력할게요");

    private final String message;
    private final static String MESSAGE_FORMAT = "\"%s\"\n";

    ServiceMessage(String message) {
        this.message = message;
    }

    public String getFormatingMessage() {
        return String.format(MESSAGE_FORMAT,message);

    }
}
