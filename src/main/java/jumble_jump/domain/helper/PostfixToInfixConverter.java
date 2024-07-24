package jumble_jump.domain.helper;

public class PostfixToInfixConverter {
/*
    public static void main(String[] args) {
        String postfix = "3 4 2 2 1 - + * 2 + +";
        System.out.println("중위 표기법: " + convert(postfix));
    }

    static String convert(String postfix) {
        Stack<String> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (isOperator(token)) {
                String b = stack.pop();
                String a = stack.pop();
                String expr = "(" + a + token + b + ")";
                stack.push(expr);
            } else {
                stack.push(token);
            }
        }
        return stack.pop();
    }

    static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

 */

    /**
     * 만약에 {2*(3+4*5)}를 후위표기법으로 변환하면
     * 2,3,4,5,),*,+,(,},*,{
     * 이다.
     * 합차기 위해서는
     * 숫자를 스택에 넣고
     * 열린괄호가 나타나면 닫힌 괄호가 나타날때까지 스택에서 뽑아서 연산자와 계산을 한다.
     *
     * )
     * 4*5
     * 3+4*5
     * (
     * open괄호와 닫힌 괄호 사이에 합친 식을 넣어 최종 스태겡 저장한다.
     *
     */
}
