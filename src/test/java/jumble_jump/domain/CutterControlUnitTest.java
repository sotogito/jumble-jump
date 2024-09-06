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
    List<RiceCake> riceCakes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cutterController = new CutterControlUnit();
        riceCakes.add(new RiceCake(10));
        riceCakes.add(new RiceCake(15));
        riceCakes.add(new RiceCake(19));
        riceCakes.add(new RiceCake(17));
    }


    @Test
    @DisplayName("절단기 길이 < 떡 = 0 초과 값 반환")
    void calculateCutRiceCakeTotalHeight_0초과_반환() {
        int midCutterHeight = 15;

        int expected = 6;
        int actual = cutterController.calculateCutRiceCakeTotalHeight(riceCakes, midCutterHeight);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("절단기 길이 > 떡 = 0 반환")
    void calculateCutRiceCakeTotalHeight_결과0_반환() {
        int midCutterHeight = 20;

        int expected = 0;
        int actual = cutterController.calculateCutRiceCakeTotalHeight(riceCakes, midCutterHeight);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("절단기와 잘린 떡이 반비례 관계인지 확인")
    void 절단기가_길면_잘린_떡의_길이가_더_짧은지_확인() {
        int longCutterHeight = 15;
        int shortCutterHeight = 5;

        int longActual = cutterController.calculateCutRiceCakeTotalHeight(riceCakes, longCutterHeight);
        int shotActual = cutterController.calculateCutRiceCakeTotalHeight(riceCakes, shortCutterHeight);

        boolean expected = true;
        boolean actual = longActual < shotActual;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("결과 > 타켓 => 절단기의 길이가 너무 짧음")
    void 절단기가_짧을_떄() {
        int target = 1;
        int result = 20;

        CutterLength expected = CutterLength.SHORT;
        CutterLength actual = cutterController.getCutterLengthStatus(target, result);
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("결과 < 타켓 => 절단기의 길이가 너무 김")
    void 절단기가_길_떄() {
        int target = 30;
        int result = 29;

        CutterLength expected = CutterLength.LONG;
        CutterLength actual = cutterController.getCutterLengthStatus(target, result);
        assertEquals(expected, actual);

    }


    @Test
    @DisplayName("결과 == 타켓 => 알맞은 절단기의 길이")
    void 알맞은_절단기_길이() {
        int target = 15;
        int result = 15;

        CutterLength expected = CutterLength.SAME;
        CutterLength actual = cutterController.getCutterLengthStatus(target, result);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("절단기가 너무 짧기 떄문에 stat index를 mid+1로 변경")
    void 절단기의_길이가_짧을_떄_START_POINT_INDEX_반환() {
        CutterLength midCutterLength = CutterLength.SHORT;
        int mid = 9;

        int expected = 9 + 1;
        int actual = cutterController.adjustingLength(midCutterLength, mid);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("절단기가 너무 길기 때문에 end index를 mid-1로 변경")
    void 절단기의_길이가_길_떄_END_POINT_INDEX_반환() {
        CutterLength midCutterLength = CutterLength.LONG;
        int mid = 9;

        int expected = 9 - 1;
        int actual = cutterController.adjustingLength(midCutterLength, mid);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("알맞는 절단기의 길")
    void 알맞은_절단기의_길이() {
        CutterLength midCutterLength = CutterLength.SAME;
        int mid = 9;

        int expected = mid;
        int actual = cutterController.adjustingLength(midCutterLength, mid);

        assertEquals(expected, actual);
    }

}