package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.gamedata.OpenState;

import java.util.InputMismatchException;

public class InputMatcher {
    //input받아서 분류해서 넘김
    //인터페이스?

    public InputMatcher(String input, Alice alice, Door door, Key key) {
        //메시지 출력까지?

        //try{
            if (isOpen(input)) {
                if(key.isCanOpen(alice)){
                    key.setOpenState(OpenState.OPEN);
                }
                return;
            } else if (isGoOver(input)) {
                if (door.isAlreadyOpenDoor(key) && door.isCanGoOver(alice)) { //크기가 알맞지 않으면 오류
                    door.setGoOverState(GoOverState.GO_OVER);
                }
                return;
            }
            alice.updateSize(Item.findGrowState(input));
        //}catch (IllegalArgumentException e){
         //   throw new IllegalArgumentException(e.getMessage());
        //}
//처음에만 앨리스인지 확인할까?
    }


    private boolean isOpen(String input) {
        return OpenState.findOpenState(input);
    }

    private boolean isGoOver(String input) {
        return GoOverState.findGoOverState(input);
    }

}
