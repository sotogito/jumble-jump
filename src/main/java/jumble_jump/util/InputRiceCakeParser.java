package jumble_jump.util;

import jumble_jump.domain.RiceCake;
import jumble_jump.repository.RiceCakes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InputRiceCakeParser {

    public static List<RiceCake> parse(String input) {
        List<RiceCake> result = new ArrayList<>();

        String[] strArray = input.split(" ");
        try {
            for (String s : strArray) {
                int num = Integer.parseInt(s.trim());

                validateRiceCakeLength(num);

                result.add(new RiceCake(num));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 입력해주세요.");
        }

        validateRiceCakesCount(result);
        validateMinMaxDifference(result);

        return result;
    }

    private static void validateRiceCakeLength(int length) {
        if (length < 1 || length > 50) {
            throw new IllegalArgumentException("떡의 길이는 1~50로 입력해주세요.");
        }
    }

    private static void validateRiceCakesCount(List<RiceCake> riceCakes) {
        int count = riceCakes.size();

        if (count < 1 || count > 20) {
            throw new IllegalArgumentException("떡은 총 1~20개까지 입력해주세요.");
        }
    }

    private static void validateMinMaxDifference(List<RiceCake> riceCakes) {
        int min = Collections.min(riceCakes, Comparator.comparingInt(RiceCake::getHeight)).getHeight();
        int max = Collections.max(riceCakes, Comparator.comparingInt(RiceCake::getHeight)).getHeight();
        int difference = max - min;

        if (difference > 10) {
            throw new IllegalArgumentException("떡의 길이 차이는 최대 10입니다.");
        }
    }

}
