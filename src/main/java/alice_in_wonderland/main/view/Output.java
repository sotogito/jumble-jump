package alice_in_wonderland.main.view;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.manager.GameManager;
import alice_in_wonderland.main.util.message.GameMessage;

public class Output {

    public static void printGameIntro(){
        System.out.println(GameMessage.intro());
    }

    public static void printAttemptCount(GameManager gameManager){
        System.out.println();
        System.out.println(gameManager);
    }

    public static void printCharacterSize(Key key, Door door, Alice alice){
        System.out.println(key);
        System.out.println(door);
        System.out.println(alice);
    }


}
