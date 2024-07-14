package jumble_jump.view;

import jumble_jump.util.message.ErrorMessage;

public class Output {
    public static void printError(String error) {
        System.out.printf(ErrorMessage.PRINT_ERROR_FORMAT,error);
    }

    public static void printMessage(String message){
        System.out.print(message);
    }
}
