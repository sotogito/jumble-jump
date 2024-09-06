package jumble_jump.view;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputRiceCakes() {
        System.out.println("Enter rice cakes: ");
        return scanner.nextLine();
    }

    public static int inputRiceCakeTotalHeight() {
        System.out.println("원하는 떡 길이를 입력해주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("떡 길이는 숫자로 입력해주세요.");
        }
    }

}
