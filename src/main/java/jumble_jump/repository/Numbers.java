package jumble_jump.repository;

import jumble_jump.domain.Token;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    private final List<Token> numbers;

    public Numbers(List<Token> numbers) {
        this.numbers = numbers;
    }

    public List<Token> getNumbers() {
        return numbers;
    }

}
