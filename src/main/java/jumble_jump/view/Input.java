package jumble_jump.view;

import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static int inputMapLengthAndWith(){
        try{
            System.out.println("맵의 가로, 세로 크기를 입력하세요. (맵은 정사각형 입니다.)");
            return Integer.parseInt(scanner.nextLine());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("맵의 크기는 숫자로 입력해주세요.\n");
        }
    }
}
