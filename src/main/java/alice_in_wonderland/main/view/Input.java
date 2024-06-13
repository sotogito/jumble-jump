package alice_in_wonderland.main.view;

import alice_in_wonderland.main.util.message.GameMessage;

import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputUser() {
        System.out.println(GameMessage.inputUserData);
        return scanner.nextLine();
    }

}
