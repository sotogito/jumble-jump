package jumble_jump.controller;

import jumble_jump.domain.Problem;
import jumble_jump.domain.matcher.NumberMatcher;
import jumble_jump.domain.matcher.OperatorMatcher;
import jumble_jump.domain.matcher.ParenthesisMatcher;
import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;
import jumble_jump.util.Tokenizer;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class MainController {

    public void main(){
        Tokenizer tokenizer = createTokenizer();
        List<Token> tokens = getTokens(tokenizer);
        setProblemTokens(tokens);

        System.out.println(Problem.getProblemText());




    }

    private void setProblemTokens(List<Token> tokens) {
        Problem.setProblemTokens(tokens);
    }

    private List<Token> getTokens(Tokenizer tokenizer) {
        while (true){
            try{
                return tokenizer.tokenize(Input.inputProblem());
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
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
