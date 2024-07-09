package jumble_jump.view;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPrintout(){
        System.out.print("출력물을 입력해 주세요.\n");
        return scanner.nextLine();
    }

    public static int inputFontId(){
        System.out.print("원하는 폰트 ID 를 입력해 주세요.\n");
        try{
            return Integer.parseInt(scanner.nextLine())-1;
        }catch (IllegalArgumentException e){
            throw  new IllegalArgumentException("숫자로 입력하세요\n");
        }
    }


}
