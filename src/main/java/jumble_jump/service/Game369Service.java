package jumble_jump.service;

import jumble_jump.domain.tokens.Clap;
import jumble_jump.domain.NumberImpl;
import jumble_jump.domain.tokens.NumberT;
import jumble_jump.domain.tokens.Token;
import jumble_jump.domain.type.GameElement;
import jumble_jump.repository.Numbers;
import jumble_jump.repository.Tokens369;
import jumble_jump.util.InputNumberParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 입력값을 숫자 리스트로 변경
 * 2. 숫자 리스트를 박수 포함 리스트로 변경
 * 3. 출력물 반환
 */
public class Game369Service {
    private final Numbers numbers;
    private final Tokens369 tokens369;
    private final ClapMaker clapMaker;

    private final String CLAP_PRINTOUT = "짝";
    private final String CLAP_FORMAT = "%s!";

    private List<String> tokenResult = new ArrayList<>();

    public Game369Service(ClapMaker clapMaker, Numbers numbers, Tokens369 tokens369) {
        this.clapMaker = clapMaker;
        this.numbers = numbers;
        this.tokens369 = tokens369;
    }

    public void make369TokenResult(String input){
        List<Integer> numberTList = updateNumberByInputNumber(input);
        List<Integer> sortedNumberTList = sortAscendingOrderNumber(numberTList);
        updateNumbersToken(sortedNumberTList);

        setToken369();
        updateTokenResult();
    }

    private void updateNumbersToken(List<Integer> numberTList){
        List<NumberT> result = new ArrayList<>();

        for(Integer number : numberTList){
            result.add(new NumberImpl(number));
        }

        numbers.setNumbers(result);
    }

    private List<Integer> sortAscendingOrderNumber(List<Integer> numberTList){
        return AscendingSortMachine.sort(numberTList);
    }

    private List<Integer> updateNumberByInputNumber(String inputNumber){
        return InputNumberParser.getNumberList(inputNumber);
    }

    private void updateTokenResult(){
        for(Token token : tokens369.getTokens()){
            if(token.getGameElement() == GameElement.CLAP){
                int clapCount = (((Clap) token)).getClap();

                tokenResult.add(clapPrintoutFormat(clapCount));
                continue;
            }
            int number = (((NumberT) token)).getNumber();
            tokenResult.add(String.valueOf(number));
        }
    }

    private String clapPrintoutFormat(int clapCount){
        return String.format(CLAP_FORMAT,
                CLAP_PRINTOUT.repeat(clapCount));
    }

    private void setToken369(){
        tokens369.setTokens(get369TokenIncludedClap());
    }
    private List<Token> get369TokenIncludedClap(){
        return clapMaker.makeClapList(numbers);
    }

    public List<String> getTokenResult() {
        return tokenResult;
    }

}