package view;

import domain.UserCashier;
import util.message.ServiceMessage;
import view.printer.Printer;

public class Output {

    public static void printError(String error) {
        System.out.println();
        System.out.print("[이런!] " + error);
    }

    public static void printMessage(String message){
        System.out.println();
        System.out.print(message);
    }

    public static void printPrinter(Printer printer){
        printer.print();
        System.out.println();
    }

    public static void printBalance(UserCashier userCashier){
        System.out.printf(ServiceMessage.PRINT_BALANCE, userCashier.getAmount());
    }

}
