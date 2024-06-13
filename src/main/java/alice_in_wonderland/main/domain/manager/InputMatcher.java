package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.states.GoOverState;
import alice_in_wonderland.main.domain.states.Item;
import alice_in_wonderland.main.domain.states.OpenState;


public class InputMatcher {

    public InputMatcher(String input, Alice alice, Door door, Key key) {
        if (isOpen(input)) {
            if (key.isCanOpen(alice)) {
                key.setOpenState(OpenState.OPEN);
                alice.setAtOpenSize();
            }
            return;
        } else if (isGoOver(input)) {
            if (door.isAlreadyOpenDoor(key) && door.isCanGoOver(alice)) {
                door.setGoOverState(GoOverState.GO_OVER);
                alice.setAtGoOverSize();
            }
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
