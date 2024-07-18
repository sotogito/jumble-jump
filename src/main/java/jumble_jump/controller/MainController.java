package jumble_jump.controller;

import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import jumble_jump.view.Input;

import java.util.List;

public class MainController {

    public void main(){
        Tokenizer tokenizer = createTokenizer();
        List<Token> tokens = tokenizer.tokenize(Input.inputProblem());

        for (Token token : tokens) {
            if(token instanceof NumberToken) {
                System.out.println(((NumberToken) token).getNumber());
            }else if(token instanceof OperatorToken) {
                System.out.println(((OperatorToken) token).getOperatorType());
            }else if(token instanceof ParenthesisToken) {
                System.out.println(((ParenthesisToken) token).getParenthesisPriority());
            }
        }


    }

    private Tokenizer createTokenizer() {
        NumberMatcher numberMatcher = new NumberMatcher();
        OperatorMatcher operatorMatcher = new OperatorMatcher();
        ParenthesisMatcher parenthesisMatcher = new ParenthesisMatcher();

        return new Tokenizer(numberMatcher, operatorMatcher, parenthesisMatcher);
    }
}
