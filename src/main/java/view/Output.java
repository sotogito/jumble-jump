package view;

import util.message.ServiceMessage;
import view.printer.ChangePrinter;
import view.printer.ItemListPrinter;
import view.printer.ReceiptPrinter;

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

    public static void printReceipt(ReceiptPrinter receiptPrinter){
        receiptPrinter.print();

    }

    public static void printChange(ChangePrinter changePrinter){
        changePrinter.print();
    }
}
