package alice_in_wonderland.main.util.message;

import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.gamedata.OpenState;

public class GameMessage {

    public static String inputUserData = "음료를 먹을까, 버섯을 먹을까?  ";




    public static String intro(){
        return "문을 넘어가기에는 내 키가 너무 커! 내 키를 줄어보자!\n"+"\n"+

                String.format("문을 열기 위해서는 '%s'이라고 입력해주세요.\n",OpenState.OPEN.getName()) +
                String.format("문을 넘어가기 위해서는 '%s'이라고 입력해주세요.\n", GoOverState.GO_OVER.getName()) +
                String.format("%s는 앨리스의 크기를 키우고, %s은 앨리스의 크기를 줄입니다.\n",
                        Item.DRINK.getName(),Item.MUSHROOM.getName()) +

                String.format("%s를 먹기 위해서는 '%s'라고 입력해주세요.\n",
                        Item.DRINK.getName(),Item.DRINK.getName()) +
                String.format("%s을 먹기 위해서는 '%s'이라고 입력해 주세요.\n",
                        Item.MUSHROOM.getName(),Item.MUSHROOM.getName() );
    }
}
