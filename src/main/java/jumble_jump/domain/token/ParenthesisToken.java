package jumble_jump.domain.token;

import jumble_jump.domain.type.ParenthesisType;

public interface ParenthesisToken extends Token {
     int getParenthesisPriority();
     String getParenthesisText();
     ParenthesisType getParenthesisType();
     boolean isOpenParenthesis();
}
