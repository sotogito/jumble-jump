package jumble_jump.domain.helper;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;
import org.springframework.expression.spel.ast.Operator;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class PostfixToInfixConverter {

    private static List<Token> intermediateStepPostfix;

    public static String getIntermediateStep(Stack<Token> operatorStack, List<Token> postfix, int startIndex){
        makeIntermediateStepPostfix(operatorStack, postfix, startIndex);
        StringBuilder result = new StringBuilder();


        for (Token token : intermediateStepPostfix) {
            if(token instanceof NumberToken){
                result.append(((NumberToken) token).getNumber());
            }else if(token instanceof OperatorToken){
                result.append(((OperatorToken) token).getOperatorType().getSymbol());
            } else if (token instanceof ParenthesisToken) {
                result.append(((ParenthesisToken)token).getParenthesisType().getSymbol());
                //짝이 하나면 .......?
                //
            }
        }//{(1+2+3*4)-5}



        return result.toString();
    }

    private static void makeIntermediateStepPostfix(Stack<Token> operatorStack, List<Token> postfix, int startIndex){
        intermediateStepPostfix = new ArrayList<>(operatorStack);
        for(int i=startIndex; i<postfix.size(); i++){
            intermediateStepPostfix.add(postfix.get(i));
        }
    }
}
