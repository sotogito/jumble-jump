package jumble_jump.util;

import jumble_jump.domain.RiceCake;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

}