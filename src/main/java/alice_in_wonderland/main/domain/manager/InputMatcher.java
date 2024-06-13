package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.gamedata.OpenState;

public class InputMatcher {
    //input받아서 분류해서 넘김
    //인터페이스?

    public void match(String input, Alice alice, Door door, Key key) {
        //어떤 boolean인지 넘겨애될 거 같은데
        //넘기지 말고 여기서 아예 set을 하자
        //메시지 출력까지?
        if(isOpen(input)){
            //return true;
            //Key의 상태 변경
            key.setOpenState(OpenState.OPEN);
            return;
        } else if (isGoOver(input)) {
           //return true;
            //Door의 상태 변경
            door.setGoOverState(GoOverState.GO_OVER,key);
            return;
        }
        alice.updateSize(Item.findGrowState(input));
    }


    private boolean isOpen(String input) {
        return OpenState.findOpenState(input);
    }

    private boolean isGoOver(String input) {
        return GoOverState.findGoOverState(input);
    }

}
