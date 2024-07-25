package jumble_jump.domain.converter;

import jumble_jump.domain.token.NumberToken;
import jumble_jump.domain.token.OperatorToken;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.domain.type.ParenthesisType;
import jumble_jump.util.DecimalPointFormatter;
import jumble_jump.util.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostfixToInfixConverter {

    private static List<Token> intermediateStepPostfix;
    private static final Stack<String> resultStack = new Stack<>();

    public static String getIntermediateStep(Stack<Token> operatorStack, List<Token> postfix, int startIndex){
        makeIntermediateStepPostfix(operatorStack, postfix, startIndex);

        for(int i  = 0; i < intermediateStepPostfix.size(); i++){
            Token token = intermediateStepPostfix.get(i);

            if(token instanceof NumberToken){
                updateNumber(token);
            } else if (token instanceof OperatorToken) {
                updateOperator(token);
            } else if (token instanceof ParenthesisToken) {
                ParenthesisToken parenthesisToken = (ParenthesisToken) token;

                if(parenthesisToken.isOpenParenthesis()){
                    //열린괄호 바로 앞에 operator이 아닐떄까지 int ++

                    int count = 0;
                    for(int j = i-1; j > 0 ; j--){
                        Token token1 = intermediateStepPostfix.get(j);
                        if(!(intermediateStepPostfix.get(j) instanceof OperatorToken)){//열린괄호의 바오 앞에 있는 연산자만
                           break;
                        }
                        count++;
                    }

                    if(count != 0){
                        String problemPart = resultStack.pop();
                        char openParenthesis = parenthesisToken.getParenthesisType().getSymbol();
                        char closeParenthesis = ParenthesisType.findCloseTypeByOpen(parenthesisToken).getSymbol();
                        resultStack.push(
                                String.format("%s%s%s", openParenthesis, problemPart, closeParenthesis)
                        );
                    }
                }
            }
        }
        return resultStack.pop();
    }

    private static void updateParenthesis(Token token, int i){
        ParenthesisToken parenthesisToken = (ParenthesisToken) token;

        if(parenthesisToken.isOpenParenthesis()){
            //열린괄호 바로 앞에 operator이 아닐떄까지 int ++

            int count = 0;
            for(int j = i-1; j > 0 ; j--){
                Token token1 = intermediateStepPostfix.get(j);
                if(!(intermediateStepPostfix.get(j) instanceof OperatorToken)){//열린괄호의 바오 앞에 있는 연산자만
                    break;
                }
                count++;
            }

            if(count > 0){
                String problemPart = resultStack.pop();
                char openParenthesis = parenthesisToken.getParenthesisType().getSymbol();
                char closeParenthesis = ParenthesisType.findCloseTypeByOpen(parenthesisToken).getSymbol();
                resultStack.push(
                        String.format("%s%s%s", openParenthesis, problemPart, closeParenthesis)
                );
            }
        }
    }

    //private static void

    private static void updateNumber(Token token){
        Number numberFormat = DecimalPointFormatter.format(((NumberToken) token).getNumber());
//
//        double number = ((NumberToken) token).getNumber();
//        if(number < 0){
//            resultStack.push("("+String.valueOf(numberFormat)+")");
//            return;
//        }


        resultStack.push(String.valueOf(numberFormat));
    }

    private static void updateOperator(Token token){
        String num2 = resultStack.pop();
        String num1 = resultStack.pop();
        char operator = ((OperatorToken) token).getOperatorType().getSymbol();
        resultStack.push(
                String.format("%s%s%s", num1, operator, num2)
        );
    }






    private static void makeIntermediateStepPostfix(Stack<Token> operatorStack, List<Token> postfix, int startIndex){
        intermediateStepPostfix = new ArrayList<>(operatorStack);
        for(int i=startIndex; i<postfix.size(); i++){
            intermediateStepPostfix.add(postfix.get(i));
        }
    }

}
