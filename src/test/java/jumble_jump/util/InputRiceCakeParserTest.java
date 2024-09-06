package jumble_jump.util;

import jumble_jump.domain.RiceCake;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputRiceCakeParserTest {

    @Test
    void 입력데이터_저장_성공(){
        String input = "10 12 15 18";
        List<Integer> expected = new ArrayList<>(List.of(10,12,15,18));
        List<Integer> actual = new ArrayList<>();

        List<RiceCake> riceCakeList = InputRiceCakeParser.parse(input);
        for (RiceCake riceCake : riceCakeList) {
            actual.add(riceCake.getHeight());
        }

        assertEquals(expected, actual);
    }

    @Test
    void 떡_길이_범위_초과_예외처리(){
        String input = "10 12 15 51";

        assertThatThrownBy(() -> InputRiceCakeParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("떡의 길이는 1~50로 입력해주세요.");
    }

    @Test
    void 떡_개수_범위_초과_예외처리(){
        String input = "1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9";

        assertThatThrownBy(() -> InputRiceCakeParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("떡은 총 1~20개까지 입력해주세요.");
    }

    @Test
    void 떡_길이_차이_예외처리(){
        String input = "1 50 12";

        assertThatThrownBy(() -> InputRiceCakeParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("떡의 길이 차이는 최대 10입니다.");
    }



}