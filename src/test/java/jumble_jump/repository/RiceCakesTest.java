package jumble_jump.repository;

import jumble_jump.domain.RiceCake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiceCakesTest {
    private RiceCakes riceCakes;

    @BeforeEach
    void setUp() {
        List<RiceCake> riceCakesList = new ArrayList<>();
        riceCakesList.add(new RiceCake(1));
        riceCakesList.add(new RiceCake(5));
        riceCakesList.add(new RiceCake(30));

        riceCakes = new RiceCakes(riceCakesList);
    }

    @Test
    void 최대_떡_길이_반환(){
        int expected = 30;
        int actual = riceCakes.getMaxRiceCakeLength();

        assertEquals(expected, actual);
    }

}