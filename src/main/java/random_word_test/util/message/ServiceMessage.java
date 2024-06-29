package random_word_test.util.message;

public enum ServiceMessage {
    INPUT_VOCA("영어단어와 뜻을 입력해주세요");

    private final String message;
    private final static String MESSAGE_FORMAT = "\"%s\"\n";

    ServiceMessage(String message) {
        this.message = message;
    }

    public String getFormatingMessage() {
        return String.format(MESSAGE_FORMAT,message);

    }
}
