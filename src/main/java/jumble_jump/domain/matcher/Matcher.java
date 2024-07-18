package jumble_jump.domain.matcher;

import jumble_jump.util.Token;

/**
 * 입력된 문제의 요소들이 숫자-연산자-괄호인지 큰 틀로 구분이 되면,
 * 더 자세히 구별하는 작업
 */
public interface Matcher {
    Token match(char input);

}
