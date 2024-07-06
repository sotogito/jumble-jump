package domain;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.KeyStore;
import java.util.EnumMap;
import java.util.Map;


class ChangeCalculatorTest {
    private ChangeCalculator changeCalculator;

    @Test
    void calculateChange() {
        changeCalculator = new ChangeCalculator();
        int balance = 7713;
        EnumMap<Bill,Integer> result = changeCalculator.calculateChange(balance);

        int expected = 10;
        int actual = result.values().stream().mapToInt(Integer::intValue).sum();

        assertEquals(expected, actual);
    }

}