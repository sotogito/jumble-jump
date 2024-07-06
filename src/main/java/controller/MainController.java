package controller;

import domain.ChangeCalculator;
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
import view.printer.ReceiptPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    public void main() throws IOException {
        Output.printWelcomeStore();
        Order order = createOrder();
        Items items = registerVendingMachineItems();
        sendItemListToOutput(items);

        UserCashier userCashier = createUserCashier(items);

        Store store = new Store(order,items,userCashier);
        getUserPurchaseItemLoop(store,userCashier);

        sendReceiptDataToOutput(userCashier,order);
        sendChangeDataToOutput(userCashier);

    }

    private void sendChangeDataToOutput(UserCashier userCashier) {
        ChangePrinter changePrinter = new ChangePrinter(userCashier);
        Output.printChange(changePrinter);
    }

    private void sendReceiptDataToOutput(UserCashier userCashier, Order order) {
        ReceiptPrinter receiptPrinter = new ReceiptPrinter(userCashier, order);
        Output.printReceipt(receiptPrinter);

    }

    private void getUserPurchaseItemLoop(Store store,UserCashier userCashier){
        while (true) {
            System.out.printf(ServiceMessage.PRINT_BALANCE, userCashier.getAmount());
            try {
                String[] inputPurchaseData = Input.inputPurchaseItemNameAndQuantity();
                String itemName = inputPurchaseData[0];
                int quantity = Integer.parseInt(inputPurchaseData[1]);

                if (!store.isCanPurchase(itemName, quantity)) {
                    break;
                }
                System.out.print(ServiceMessage.PRINT_SUCCESS_SHOPPING_BASKET);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                Output.printError(e.getMessage());
            }
        }
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

    private void sendItemListToOutput(Items items) {
        ItemListPrinter itemListPrinter = new ItemListPrinter(items);
        Output.printItemList(itemListPrinter);
    }


    private Items registerVendingMachineItems() throws IOException {
        ItemCsvReader csvReader = new ItemCsvReader(new RawCsvReader());
        String itemCsvPath = "/data/items.csv";

        List<Item> itemList = csvReader.readItems(itemCsvPath);
        return new Items(itemList);
    }

    private Order createOrder() {
        return new Order();
    }


}
