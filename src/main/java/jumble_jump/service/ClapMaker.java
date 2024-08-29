package jumble_jump.service;

import jumble_jump.domain.ClapImpl;
import jumble_jump.domain.tokens.NumberT;
import jumble_jump.domain.tokens.Token;
import jumble_jump.domain.type.Game369Number;
import jumble_jump.repository.Numbers;

import java.util.ArrayList;
import java.util.List;

public class ClapMaker {

    private final List<Integer> NUMBER_LIST_369 = Game369Number.get369NumberList();

    public ClapMaker() {
    }

    public List<Token> makeClapList(Numbers numbers) {
        List<Token> result = new ArrayList<>();

        for (NumberT numberToken : numbers.getNumbers()) {
            int clapCount = getClapCount(numberToken);

            if (clapCount > 0) {
                result.add(new ClapImpl(clapCount)); //note 박수로 넣음
                continue;
            }
            result.add(numberToken); //note 그냥 숫자로 넣음
        }
        return result;
    }

    private int getClapCount(NumberT numberT) {
        int clapResult = 0;

        int number = numberT.getNumber();
        for (Integer numToken : getNumberListSeperatedByDigit(number)) {
            if (is369Number(numToken)) {
                clapResult++;
            }
        }
        return clapResult;
    }

    private List<Integer> getNumberListSeperatedByDigit(int number) {
        List<Integer> result = new ArrayList<>();

        String numberStr = String.valueOf(number);
        for (char ch : numberStr.toCharArray()) {
            result.add(Character.getNumericValue(ch));
        }
        return result;
    }

    private boolean is369Number(int numToken) {
        return NUMBER_LIST_369.contains(numToken);
    }

}
