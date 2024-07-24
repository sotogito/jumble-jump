package jumble_jump.util;

import jumble_jump.domain.token.NumberToken;
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

        // 결과 출력 (테스트용)
        for (char c : chars) {
            System.out.print(c);
        }

        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (Character.isDigit(c)) {
                while (isNotSingleDigitNumber(i,chars)) {
                    numberBuilder.append(chars[i]);
                    i++;
                }
                result.add(getNumberToken(Double.parseDouble(numberBuilder.toString())));
                numberBuilder = new StringBuilder();
                i--;
                continue;
            } else if (OperatorType.isOperatorType(c)) {
                if (handleSignAtStart(c, i, numberBuilder)) {
                    continue;
                }
                OperatorTokenizeValidator.validateLastOperator(i,chars.length);
                result.add(getOperatorToken(c));
                continue;
            } else if (ParenthesisType.isParenthesisType(c)) {
                result.add(getParathesisToken(c));
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

    private boolean handleSignAtStart(char c, int i, StringBuilder numberBuilder) {
        if (i == 0 && OperatorTokenizeValidator.isNumberSign(c)) {
            numberBuilder.append(c);
            return true;
        }
        return false;
    }

    private boolean isNotSingleDigitNumber(int i, char[] chars) {
        return i < chars.length && (Character.isDigit(chars[i]) || chars[i] == '.');
    }








}
