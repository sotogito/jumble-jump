package jumble_jump.util;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.domain.token.parenthesis.Parenthesis;
import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.util.validator.tokenizer.OperatorTokenizeValidator;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final NumberMatcher numberMatcher;
    private final OperatorMatcher operatorMatcher;
    private final ParenthesisMatcher parenthesisMatcher;

    public Tokenizer(
            NumberMatcher numberMatcher, OperatorMatcher operatorMatcher, ParenthesisMatcher parenthesisMatcher) {
        this.numberMatcher = numberMatcher;
        this.operatorMatcher = operatorMatcher;
        this.parenthesisMatcher = parenthesisMatcher;
    }

    public List<Token> tokenize(String inputProblem) {
        if(inputProblem == null || inputProblem.isEmpty()) {
            throw new IllegalArgumentException("식이 비어있어요.");
        }

        List<Token> result = new ArrayList<>();
        char[] chars = inputProblem.toCharArray();

        StringBuilder trimmedInput = new StringBuilder();
        for (char c : chars) {
            if (!Character.isWhitespace(c)) {
                trimmedInput.append(c);
            }
        }
        chars = trimmedInput.toString().toCharArray();


        StringBuilder numberBuilder = new StringBuilder();
        boolean isFirstNumber = true;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
/**
 * while문으로 안되는 조건일떄까지 변경해야될듯
 */
            if (Character.isDigit(c)) {
                isFirstNumber = false;
                while (isNotSingleDigitNumber(i,chars)) {
                    numberBuilder.append(chars[i]);
                    i++;
                }
                result.add(getNumberToken(Double.parseDouble(numberBuilder.toString())));
                numberBuilder = new StringBuilder();
                i--;

                if (i + 1 < chars.length && ParenthesisType.isParenthesisType(chars[i + 1])) {
                    ParenthesisType type = ParenthesisType.fromSymbol(chars[i + 1]);
                    if (type.isOpen()) {
                        result.add(getOperatorToken(OperatorType.MULTIPLY.getSymbol()));
                    }
                }


                continue;
            }

            else if (OperatorType.isOperatorType(c)) {
                if (handleSignAtStart(c, isFirstNumber, numberBuilder)) {
                    if(ParenthesisType.isParenthesisType(chars[i+1])){
                        numberBuilder.append(1);
                        result.add(getNumberToken(Double.parseDouble(numberBuilder.toString())));
                        result.add(getOperatorToken(OperatorType.MULTIPLY.getSymbol()));
                        numberBuilder = new StringBuilder();
                    }
                    continue;
                }
                if(OperatorType.isOperatorType(chars[i+1])){
                    isFirstNumber = true;
                }

                OperatorTokenizeValidator.validateLastOperator(i,chars.length);
                result.add(getOperatorToken(c));
                continue;
            }


            else if (ParenthesisType.isParenthesisType(c)) {
                ParenthesisToken nowParenthesisToken = (ParenthesisToken) getParathesisToken(c);
                result.add(nowParenthesisToken);

                if(nowParenthesisToken.isOpenParenthesis()){
                    isFirstNumber = true;
                }
                if(i != chars.length - 1) {
                    if(!nowParenthesisToken.isOpenParenthesis() && Character.isDigit(chars[i+1])){ //닫힌 괄호 다음 숫자
                        result.add(getOperatorToken(OperatorType.MULTIPLY.getSymbol()));
                    }
                }

                continue;
            }

            throw new IllegalArgumentException("잘못된 양식의 식입니다.");
        }
        return result;

    }

    private Token getNumberToken(Double c){
        return numberMatcher.match(c);
    }

    private Token getOperatorToken(char c){
        return operatorMatcher.match(c);
    }

    private Token getParathesisToken(char c){
        return parenthesisMatcher.match(c);
    }

    private boolean handleSignAtStart(char c, boolean isFirstNumber, StringBuilder numberBuilder) {
        if (isFirstNumber && OperatorTokenizeValidator.isNumberSign(c)) {
            numberBuilder.append(c);
            return true;
        }
        return false;
    }

    private boolean isNotSingleDigitNumber(int i, char[] chars) {
        return i < chars.length && (Character.isDigit(chars[i]) || chars[i] == '.');
    }
    private boolean isPreviousCharOperator(List<Token> tokens) {
        if (tokens.isEmpty()) {
            return false;
        }
        Token lastToken = tokens.get(tokens.size() - 1);
        return lastToken instanceof OperatorToken;
    }

    private boolean isPreviousCharOpenParenthesis(List<Token> tokens) {
        if (tokens.isEmpty()) {
            return false;
        }
        Token lastToken = tokens.get(tokens.size() - 1);
        return lastToken instanceof ParenthesisToken && ((ParenthesisToken) lastToken).getParenthesisType().isOpen();
    }








}
