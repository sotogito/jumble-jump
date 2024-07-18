package jumble_jump.view;

import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputProblem(){
        System.out.print("문제를 입력해 주세요. : \n");
        return scanner.nextLine();
    }
}
