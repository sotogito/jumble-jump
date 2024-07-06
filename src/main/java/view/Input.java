package view;

import util.message.ErrorMessage;
import util.message.ServiceMessage;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static long inputUSerAmount(){
        System.out.print(ServiceMessage.INPUT_USER_AMOUNT);
        try{
            return Long.parseLong(scanner.next());
        }catch (IllegalArgumentException e){
            throw new NumberFormatException(ErrorMessage.ONLY_NUMBER);
        }
    }
}
