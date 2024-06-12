package alice_in_wonderland.main.domain.manager;

import alice_in_wonderland.main.domain.gamedata.GoOverState;
import alice_in_wonderland.main.domain.gamedata.Item;
import alice_in_wonderland.main.domain.gamedata.OpenState;

public class InputMatcher {
    //input받아서 분류해서 넘김
    //인터페이스?

    public boolean match(String input) {
        if(isOpen(input)){
            return true;
        } else if (isGoOver(input)) {
            return true;
        }
        return isItem(input);
    }

    private boolean isItem(String input) {
        return Item.findGrowState(input);
    }

    private boolean isOpen(String input) {
        return OpenState.findOpenState(input);
    }

    private boolean isGoOver(String input) {
        return GoOverState.findGoOverState(input);
    }

}
