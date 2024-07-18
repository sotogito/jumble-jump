package jumble_jump.util.converter;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.type.OperatorType;
import jumble_jump.util.Token;

import java.util.ArrayList;
import java.util.List;

public class ProblemToStringConverter {

    public static String getProblemText(List<Token> problemTokens) {
        List<String> result = new ArrayList<>();
        for (Token token : problemTokens) {
            if(token instanceof NumberToken) {
                int number = ((NumberToken) token).getNumber();
                result.add(String.valueOf(number));
            }else if(token instanceof OperatorToken) {
                OperatorType operatorType = ((OperatorToken) token).getOperatorType();
                result.add(Character.toString(operatorType.getSymbol()));
            }else if(token instanceof ParenthesisToken) {
                result.add(((ParenthesisToken) token).getParenthesisText());
            }
        }

        return String.join(" ", result);
    }

}
