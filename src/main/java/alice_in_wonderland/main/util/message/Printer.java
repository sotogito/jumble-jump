package alice_in_wonderland.main.util.message;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.gamedata.OpenState;
import alice_in_wonderland.main.domain.manager.GameManager;

public class Printer {

    public static String intro(){
        return "문을 넘어가기에는 내 키가 너무 커! 내 키를 줄어보자!\n"+"\n"+
                String.format("문을 열기 위해서는 '%s'이라고 입력해주세요.\n", OpenState.OPEN.getName()) +
                String.format("문을 넘어가기 위해서는 '%s'이라고 입력해주세요.\n", GoOverState.GO_OVER.getName()) +
                String.format("%s는 앨리스의 크기를 키우고, %s은 앨리스의 크기를 줄입니다.\n",
                        Item.DRINK.getName(),Item.MUSHROOM.getName()) +
                String.format("%s를 먹기 위해서는 '%s'라고 입력해주세요.\n",
                        Item.DRINK.getName(),Item.DRINK.getName()) +
                String.format("%s을 먹기 위해서는 '%s'이라고 입력해 주세요.\n",
                        Item.MUSHROOM.getName(),Item.MUSHROOM.getName() );
    }

    public static String success(GameManager gameManager, Alice alice){
        return String.format("%d번만에 성공하셨습니다\n",gameManager.getAttemptCount())+
                alice+ "\n"+
                String.format("앨리스의 크기가 %d일때 문을 열었어요.\n",alice.getAtOpenSize())+
                String.format("앨리스의 크기가 %d일때 넘어갔어요.\n",alice.getAtGoOverSize())+
                "또 만나요";
    }

    public static String fail(Alice alice, Door door){
        String doorState = "앨리스는 문을 열지 못했어요\n";
        if(door.getGoOverState()){
             doorState = String.format("앨리스의 크기가 %d일때 문을 열었어요.\n",alice.getAtOpenSize());
        }

        return "이럴수가! 실패했습니다!\n"+
                doorState+
                "앨리스는 문을 넘지 못했어요\n"+
                "또 보지말아요ㅜㅜ";
    }

}
