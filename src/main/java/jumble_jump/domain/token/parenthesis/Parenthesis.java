package jumble_jump.domain.token.parenthesis;

import jumble_jump.domain.component.ParenthesisType;
import jumble_jump.domain.token.ParenthesisToken;
import jumble_jump.util.Token;

/**
 * 객체들의 인터페이스 implments는 부모가 아니라 자식으로 해줘야함
 * 그래야 나중에 List<Token>에서 instanceof으로 숫자인지, 연잔자인지, 괄호인지 알 수 있고
 * 자식 인터페이스의 메서드를 사용하여 계산할 수 있음
 */
public class Parenthesis implements ParenthesisToken {

    private final ParenthesisType parenthesisType;

    public Parenthesis(char parenthesis) {
        this.parenthesisType = ParenthesisType.fromSymbol(parenthesis);
    }

    @Override
    public int getParenthesisPriority() {
        return parenthesisType.getPriority();
    }

    @Override
    public String getParenthesisText() {
        return Character.toString(parenthesisType.getSymbol());
    }
}
