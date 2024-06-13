package alice_in_wonderland.main.util.message;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.manager.GameManager;

public class Printer {

    public static String success(GameManager gameManager, Alice alice, Door door, Key key){
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
