package view;

import util.message.ServiceMessage;
import view.printer.ItemListPrinter;

public class Output {

    public static void printError(String error) {
        System.out.println();
        System.out.print("[이런!] " + error);
    }
    public static void printWelcomeStore(){
        System.out.print(ServiceMessage.PRINT_WELCOME_STORE);
        System.out.println();
    }

    public static void printItemList(ItemListPrinter itemListPrinter){
        itemListPrinter.print();
        System.out.println();
    }
}
