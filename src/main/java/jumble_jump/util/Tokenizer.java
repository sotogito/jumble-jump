package jumble_jump.util;

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
        List<Token> result = new ArrayList<>();
        char[] chars = inputProblem.toCharArray();

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
