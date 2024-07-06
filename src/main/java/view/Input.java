package view;

import domain.InputPurchaseItemParser;
import util.message.ErrorMessage;
import util.message.ServiceMessage;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static long inputUSerAmount(){
        System.out.print(ServiceMessage.INPUT_USER_AMOUNT);
        try{
            return Long.parseLong(scanner.nextLine().trim());
        }catch (IllegalArgumentException e){
            throw new NumberFormatException(ErrorMessage.ONLY_NUMBER);
        }
    }

    public static String[] inputPurchaseItemNameAndQuantity(){
        System.out.print(ServiceMessage.INPUT_PURCHASE_ITEM_NAME_QUANTITY);
        try{
            String[] inputData = InputPurchaseItemParser.parse(scanner.nextLine().trim());
            Integer.parseInt(inputData[1]);
            return inputData;
        }catch (NumberFormatException e){
            throw new NumberFormatException(ErrorMessage.ONLY_NUMBER_PURCHASE_QUANTITY);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        }
    }

}
