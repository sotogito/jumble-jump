package jumble_jump.domain.converter.token;

import jumble_jump.domain.Resettable;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.Token;
import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.util.validator.tokenizer.OperatorTokenizeValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Tokenizer implements TokenizeConvertor , Resettable {
    private final NumberMatcher numberMatcher;
    private final OperatorMatcher operatorMatcher;
    private final ParenthesisMatcher parenthesisMatcher;

    private char[] problemChars;
    private boolean isFirstNumber = true;
    private StringBuilder numberBuilder = new StringBuilder();
    
    private List<Token> tokenizedResult = new ArrayList<>();

    public Tokenizer(
            NumberMatcher numberMatcher, OperatorMatcher operatorMatcher, ParenthesisMatcher parenthesisMatcher) {
        this.numberMatcher = numberMatcher;
        this.operatorMatcher = operatorMatcher;
        this.parenthesisMatcher = parenthesisMatcher;
    }

    public List<Token> tokenize(String inputProblem){
        reset();

        problemToChars(inputProblem);
        return getTokenizedResult(inputProblem);

    }


    public List<Token> getTokenizedResult(String inputProblem) {
        problemToChars(inputProblem);

        for (int i = 0; i < problemChars.length; i++) {
            char c = problemChars[i];

            if (Character.isDigit(c)) { //note 숫자
                i = updateNumber(i);
                handleNumberFollowedByParenthesis(tokenizedResult,i);
                continue;
            } else if (OperatorType.isOperatorType(c)) {  //note 연산자
                if(handleOperatorAtStartFollowedByParenthesis(c,i)){
                    continue;
                }
                handleOperatorFollowedByOperator(i);
                i = updateOperator(c,i);
                continue;
            } else if (ParenthesisType.isParenthesisType(c)) { //note 괄호
                ParenthesisToken nowParenthesisToken = (ParenthesisToken) getParathesisToken(c);
                i = updateParenthesis(nowParenthesisToken,i);

                handleOpenParenthesisToResetFirstNumber(nowParenthesisToken);
                handleCloseParenthesisFollowedByNumber(nowParenthesisToken,i);
                continue;
            }
            throw new IllegalArgumentException("잘못된 양식의 식입니다.- tk");
        }
        return tokenizedResult;
    }

    public int updateNumber(int index){
        isFirstNumber = false;
        int updateIndex = handleMultiDigitNumber(numberBuilder, index);
        index += updateIndex-1; //note 숫자로 집어 넣은 마지막 인덱스 만큼이 현재 인덱스여야함.

        tokenizedResult.add(getNumberToken(Double.parseDouble(numberBuilder.toString()))); //함수화
        numberBuilder = new StringBuilder();

        return index;
    }

    public int updateOperator(char c,int index){
        OperatorTokenizeValidator.validateLastOperator(index, problemChars.length);
        tokenizedResult.add(getOperatorToken(c));
        return index;
    }

    public int updateParenthesis(ParenthesisToken nowParenthesisToken, int index){
        tokenizedResult.add(nowParenthesisToken);
        return index;
    }



    private void handleCloseParenthesisFollowedByNumber(ParenthesisToken nowParenthesisToken, int index){
        if (index != problemChars.length - 1) {
            if (!nowParenthesisToken.isOpenParenthesis() && Character.isDigit(problemChars[index + 1])) { //닫힌 괄호 다음 숫자
                tokenizedResult.add(getOperatorToken(OperatorType.MULTIPLY.getSymbol()));
            }
        }
    }

    private void handleOpenParenthesisToResetFirstNumber(ParenthesisToken nowParenthesisToken) {
        if (nowParenthesisToken.isOpenParenthesis()) {
            isFirstNumber = true;
        }
    }


    private boolean handleOperatorAtStartFollowedByParenthesis(char c,int index){
        if (handleSignAtStart(c, numberBuilder)) { //note -()
            if (isNextCharIsParenthesis(index)) {
                numberBuilder.append(1);
                tokenizedResult.add(getNumberToken(Double.parseDouble(numberBuilder.toString())));
                tokenizedResult.add(getOperatorToken(OperatorType.MULTIPLY.getSymbol()));
                numberBuilder = new StringBuilder();
            }
            return true;
        }
        return false;
    }

    private void handleOperatorFollowedByOperator(int index){
        if (isNextCharIsOperator(index)) { //note * -2
            isFirstNumber = true;
        }
    }

    private int handleMultiDigitNumber(StringBuilder numberBuilder , int index){
        int updateIndex = 0;
        while (isNotSingleDigitNumber(index, problemChars)) {  //note 숫자 뒤에 숫자 or 소수점
            numberBuilder.append(problemChars[index]);
            index++;
            updateIndex++;
        }
        return updateIndex;
    }

    public void handleNumberFollowedByParenthesis(List<Token> result, int index){
        if (isNextCharIsParenthesis(index)) { //note 6(1+1)
            ParenthesisType type = ParenthesisType.fromSymbol(problemChars[index + 1]);
            if (type.isOpen()) {
                result.add(getOperatorToken(OperatorType.MULTIPLY.getSymbol()));
            }
        }
    }

    private boolean handleSignAtStart(char c, StringBuilder numberBuilder) {
        if (isFirstNumber && OperatorTokenizeValidator.isNumberSign(c)) {
            numberBuilder.append(c);
            return true;
        }
        return false;
    }

    private boolean isNextCharIsOperator(int index){
        return isNextCharWithinBounds(index) && OperatorType.isOperatorType(problemChars[index + 1]);
    }

    private boolean isNextCharIsParenthesis(int index){
        return isNextCharWithinBounds(index) && ParenthesisType.isParenthesisType(problemChars[index + 1]);
    }

    private boolean isNextCharWithinBounds(int index) {
        return index >= 0 && index + 1 < problemChars.length;

    }

    private boolean isNotSingleDigitNumber(int i, char[] chars) {
        return i < chars.length && (Character.isDigit(chars[i]) || chars[i] == '.');
    }


    private Token getNumberToken(Double c) {
        return numberMatcher.match(c);
    }

    private Token getOperatorToken(char c) {
        return operatorMatcher.match(c);
    }

    private Token getParathesisToken(char c) {
        return parenthesisMatcher.match(c);
    }


    public void problemToChars(String problemString){
        if (problemString == null || problemString.isEmpty()) {
            throw new IllegalArgumentException("식이 비어있어요.");
        }
        problemChars = problemString.toCharArray();

        StringBuilder trimmedInput = new StringBuilder();
        for (char c : problemChars) {
            if (!Character.isWhitespace(c)) {
                trimmedInput.append(c);
            }
        }
        problemChars = trimmedInput.toString().toCharArray();
    }

    public void reset(){
        tokenizedResult = new ArrayList<>();
        isFirstNumber = true;
    }


}
