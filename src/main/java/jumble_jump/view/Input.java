package jumble_jump.view;

import jumble_jump.util.message.ErrorMessage;
import jumble_jump.util.message.ServiceMessage;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPrintout(){
        System.out.print(ServiceMessage.INPUT_PRINTOUT);
        return scanner.nextLine();
    }

    public static int inputFontId(){
        System.out.print(ServiceMessage.INPUT_FONT_ID);
        try{
            return Integer.parseInt(scanner.nextLine())-1;
        }catch (IllegalArgumentException e){
            throw  new IllegalArgumentException(ErrorMessage.ONLY_NUMBER_FONT_ID);
        }
    }


}
