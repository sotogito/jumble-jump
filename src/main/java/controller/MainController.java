package controller;

import domain.Order;
import domain.Store;
import domain.UserCashier;
import domain.item.Item;
import domain.item.Items;
import util.message.ServiceMessage;
import util.reader.ItemCsvReader;
import util.reader.RawCsvReader;
import view.Input;
import view.Output;
import view.printer.ChangePrinter;
import view.printer.ItemListPrinter;
import view.printer.Printer;
import view.printer.ReceiptPrinter;

import java.io.IOException;
import java.util.List;

public class MainController {
    private Printer printer;

    public void main() throws IOException {
        Output.printMessage(ServiceMessage.PRINT_WELCOME_STORE);
        Order order = createOrder();
        Items items = registerVendingMachineItems();
        printItemListToOutput(items);

        UserCashier userCashier = createUserCashier(items);

        Store store = new Store(order,items,userCashier);
        getUserPurchaseItemLoop(store,userCashier);

        printClosingMessage();
        printReceiptDataToOutput(userCashier,order);
        printChangeDataToOutput(userCashier);
    }

    private Items registerVendingMachineItems() throws IOException {
        ItemCsvReader csvReader = new ItemCsvReader(new RawCsvReader());
        String itemCsvPath = "/data/items.csv";

        List<Item> itemList = csvReader.readItems(itemCsvPath);
        return new Items(itemList);
    }

    private void getUserPurchaseItemLoop(Store store,UserCashier userCashier){
        Output.printMessage("");
        while (true) {
            Output.printBalance(userCashier);
            try {
                if(!isCanUserPurchase(store)){
                    break;
                }
                Output.printMessage(ServiceMessage.PRINT_SUCCESS_SHOPPING_BASKET);
                //NOTE : 에러 두개 잡기
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                Output.printError(e.getMessage());
            }
        }
    }

    private boolean isCanUserPurchase(Store store){
        String[] inputPurchaseData = Input.inputPurchaseItemNameAndQuantity();
        String itemName = inputPurchaseData[0];
        int quantity = Integer.parseInt(inputPurchaseData[1]);

        return store.isCanPurchase(itemName, quantity);
    }

    private UserCashier createUserCashier(Items items) {
        while (true){
            try{
                return new UserCashier(Input.inputUSerAmount(),items);
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
            }
        }
    }

    private void printItemListToOutput(Items items) {
        printer = new ItemListPrinter(items);
        Output.printPrinter(printer);
    }

    private Order createOrder() {
        return new Order();
    }

    private void printClosingMessage(){
        Output.printMessage("");
        Output.printMessage(ServiceMessage.PRINT_CLOSING);
    }

    private void printReceiptDataToOutput(UserCashier userCashier, Order order) {
        printer = new ReceiptPrinter(userCashier, order);
        Output.printPrinter(printer);

    }

    private void printChangeDataToOutput(UserCashier userCashier) {
        printer = new ChangePrinter(userCashier);
        Output.printPrinter(printer);
    }

}
