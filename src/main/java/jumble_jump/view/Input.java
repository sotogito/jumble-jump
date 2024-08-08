package jumble_jump.view;

import java.util.List;
import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static int inputMapLengthAndWith() {
        try {
            System.out.println("맵의 가로, 세로 크기를 입력하세요. (맵은 정사각형 입니다.)");
            return Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("맵의 크기는 숫자로 입력해주세요.\n");
        }
    }

    public static String inputUserStartPointAndDirection() {
        System.out.println("x,y 시작 위치와, 방향을 입력해주세요 ex) 1 2 2");
        return scanner.nextLine();
    }

    public static String inputMapTypesLine(int lineCount) {
        System.out.printf("%d번째 맵을 생성해주세요. ex) 1 1 0 1%n", lineCount);
        return scanner.nextLine();
    }

}
