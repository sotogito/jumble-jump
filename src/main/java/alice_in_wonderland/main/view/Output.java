package alice_in_wonderland.main.view;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.manager.GameManager;
import alice_in_wonderland.main.util.message.GameMessage;
import alice_in_wonderland.main.util.message.Printer;

public class Output {

    public static void printGameIntro(){
        System.out.println(Printer.intro());
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

    public static void printSuccess(GameManager gameManager, Alice alice){
        System.out.println();
        System.out.println();
        System.out.println(Printer.success(gameManager, alice));
    }

    public static void printFail(Alice alice, Door door){
        System.out.println();
        System.out.println();
        System.out.println(Printer.fail(alice, door));
    }


}
