package jumble_jump.service;

import jumble_jump.domain.ClapCounter;
import jumble_jump.domain.ClapImpl;
import jumble_jump.domain.tokens.NumberT;
import jumble_jump.domain.tokens.Token;
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
    private final ClapCounter clapCounter;
    private static final int MIN_CLAP_COUNT = 1;

    public ClapMaker(ClapCounter clapCounter) {
        this.clapCounter = clapCounter;
    }

    public List<Token> makeClapList(Numbers numbers) {
        List<Token> result = new ArrayList<>();

        for (NumberT numberToken : numbers.getNumbers()) {
            int clapCount = clapCounter.getClapCount(numberToken);

            if (isClap(clapCount)) {
                handleClapToken(result, clapCount);
                continue;
            }
            handleNumberToken(result, numberToken);
        }
        return result;
    }

    private void handleClapToken(List<Token> result, int clapCount) {
        result.add(new ClapImpl(clapCount)); //Token>Clap 인터페이스 = new ClapImpl,
        //note ClapImpl이 Clap인터페이스로 적용되어서 부모 Token이 아닌 자식 Clap으로 리스크에 들어감
    }

    private void handleNumberToken(List<Token> result, NumberT numberToken) {
        result.add(numberToken);
    }

    private boolean isClap(int clapCount) {
        return clapCount >= MIN_CLAP_COUNT;
    }

}
