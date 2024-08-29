package jumble_jump.repository;

import jumble_jump.domain.tokens.NumberT;

import java.util.List;

public class Numbers {
    private List<NumberT> numbers;

    public Numbers() {
    }

    public void setNumbers(List<NumberT> numbers) {
        this.numbers = numbers;
    }

    public List<NumberT> getNumbers() {
        return numbers;
    }

}
