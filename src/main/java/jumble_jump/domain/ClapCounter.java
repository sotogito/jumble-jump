package jumble_jump.domain;

import jumble_jump.domain.tokens.NumberT;
import jumble_jump.domain.type.Game369Number;

import java.util.ArrayList;
import java.util.List;

public class ClapCounter {
    private static final List<Integer> NUMBER_LIST_369 = Game369Number.get369NumberList();

    public int getClapCount(NumberT numberT) {
        int clapResult = 0;

        int number = numberT.getNumber();
        for (Integer numToken : getNumberListSeperatedByDigit(number)) {
            if (is369Number(numToken)) {
                clapResult++;
            }
        }
        return clapResult;
    }

    private static List<Integer> getNumberListSeperatedByDigit(int number) {
        List<Integer> result = new ArrayList<>();

        String numberStr = String.valueOf(number);
        for (char ch : numberStr.toCharArray()) {
            result.add(Character.getNumericValue(ch));
        }
        return result;
    }

    private static boolean is369Number(int numToken) {
        return NUMBER_LIST_369.contains(numToken);
    }

}
