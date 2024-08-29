package jumble_jump.service;

import jumble_jump.domain.ClapImpl;
import jumble_jump.domain.tokens.NumberT;
import jumble_jump.domain.tokens.Token;
import jumble_jump.domain.type.Game369Number;
import jumble_jump.repository.Numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 326를 뗴를들어 설명
 * 3,2,6 으로 분리
 * NUMBER_LIST_369에 각 숫자들이 포함되는지 확인
 * clapcount = 2
 * 1개 이상이므로 clapToken으로 처리
 */
public class ClapMaker {
    private static final int MIN_CLAP_COUNT = 1;
    private static final List<Integer> NUMBER_LIST_369 = Game369Number.get369NumberList();

    public ClapMaker() {
    }

    public List<Token> makeClapList(Numbers numbers) {
        List<Token> result = new ArrayList<>();

        for (NumberT numberToken : numbers.getNumbers()) {
            int clapCount = getClapCount(numberToken);

            if (isClap(clapCount)) {
                handleClapToken(result,clapCount);
                continue;
            }
            handleNumberToken(result,numberToken);
        }
        return result;
    }

    private void handleClapToken(List<Token> result, int clapCount){
        result.add(new ClapImpl(clapCount)); //Token>Clap 인터페이스 = new ClapImpl,
        //note ClapImpl이 Clap인터페이스로 적용되어서 부모 Token이 아닌 자식 Clap으로 리스크에 들어감
    }

    private void handleNumberToken(List<Token> result,NumberT numberToken){
        result.add(numberToken);
    }

    private boolean isClap(int clapCount){
        return clapCount >= MIN_CLAP_COUNT;
    }


    //분리할까?
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
