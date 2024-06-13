package alice_in_wonderland.main.util.message;

import alice_in_wonderland.main.domain.states.GoOverState;
import alice_in_wonderland.main.domain.states.Item;
import alice_in_wonderland.main.domain.states.OpenState;

public class ErrorMessage {

    public final static String INPUT_ERROR =
            String.format("%s 중 하나를 입력해주세요.",
                    String.join(", ",
                            Item.DRINK.getName(),Item.MUSHROOM.getName(),
                            OpenState.OPEN.getName(),
                            GoOverState.GO_OVER.getName()));

    //앨리스
    public final static String ALICE_SIZE_LIMIT_MAX = "더이상 커질 수 없어ㅜㅜ";
    public final static String ALICE_SIZE_LIMIT_MIN = "더이상 작아질 수 없어";
    public final static String CANT_OPEN_WITH_KEY = "키가 너무 무거워서 들수 없어!";
    public final static String CANT_GO_OVER_BIG = "문을 넘어가기에는 내가 너무 커";
    public final static String CANT_GO_OVER_SMALL = "문을 넘어가기에는 내가 너무 작아";

    //문
    public final static String CANT_GO_OVER_NOT_YET_OPEN = "키로 문 먼저 열어야해!";

}
