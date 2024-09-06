package jumble_jump.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CutterControlUnitTest {

    private CutterController cutterController;

    @BeforeEach
    void setUp() {
        cutterController = new CutterControlUnit();
    }


    @Test
    @DisplayName("절단기 길이 < 떡 = 0 초과 값 반환")
    void calculateCutRiceCakeTotalHeight_0초과_반환(){
        List<RiceCake> riceCakes = new ArrayList<>();
        riceCakes.add(new RiceCake(10));
        riceCakes.add(new RiceCake(15));
        riceCakes.add(new RiceCake(19));
        riceCakes.add(new RiceCake(17));

        int midCutterHeight = 15;

        int expected = 6;
        int  actual= cutterController.calculateCutRiceCakeTotalHeight(riceCakes,midCutterHeight);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("절단기 길이 > 떡 = 0 반환")
    void calculateCutRiceCakeTotalHeight_결과0_반환(){
        List<RiceCake> riceCakes = new ArrayList<>();
        riceCakes.add(new RiceCake(10));
        riceCakes.add(new RiceCake(15));
        riceCakes.add(new RiceCake(19));
        riceCakes.add(new RiceCake(17));

        int midCutterHeight = 20;

        int expected = 0;
        int  actual= cutterController.calculateCutRiceCakeTotalHeight(riceCakes,midCutterHeight);

        assertEquals(expected, actual);
    }



}