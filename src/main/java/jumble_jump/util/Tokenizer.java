package jumble_jump.util;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.number.Number;
import jumble_jump.domain.type.OperatorType;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;

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



        /*
        for (char c : chars) {
            if(Character.isDigit(c)){
                result.add(getNumberToken(c));
                continue;
            } else if (OperatorType.isOperatorType(c)) {
                result.add(getOperatorToken(c));
                continue;
            } else if (ParenthesisType.isParenthesisType(c)) {
                result.add(getParathesisToken(c));
                continue;
            }
            throw new IllegalArgumentException("잘못된 양식의 식입니다.");
        }

         */

        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                StringBuilder numberBuilder = new StringBuilder();
                while (i < chars.length && (Character.isDigit(chars[i]) || chars[i] == '.')) {
                    numberBuilder.append(chars[i]);
                    i++;
                }
                result.add(new Number(Double.parseDouble(numberBuilder.toString())));

            } else if (OperatorType.isOperatorType(c)) {
                result.add(getOperatorToken(c));
                i++;
            } else if (ParenthesisType.isParenthesisType(c)) {
                result.add(getParathesisToken(c));
                i++;
            } else {
                throw new IllegalArgumentException("잘못된 양식의 식입니다.");
            }
        }
        return result;
    }

    private Token getNumberToken(char c){
        return numberMatcher.match(c);
    }

    private Token getOperatorToken(char c){
        return operatorMatcher.match(c);
    }

    private Token getParathesisToken(char c){
        return parenthesisMatcher.match(c);
    }




}
