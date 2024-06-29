package random_word_test.domain.voca;

import java.util.List;

public class Korean {
    private final List<String> definitions;

    public Korean(List<String> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return String.join(", ", definitions);
    }
}
