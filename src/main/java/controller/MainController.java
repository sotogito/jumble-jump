package controller;

import domain.Order;
import domain.UserCashier;
import domain.item.Item;
import domain.item.Items;
import util.reader.ItemCsvReader;
import util.reader.RawCsvReader;
import view.Input;
import view.Output;
import view.printer.ItemListPrinter;

import java.io.IOException;
import java.util.List;

public class MainController {
    public void main() throws IOException {
        Output.printWelcomeStore();
        Items items = registerVendingMachineItems();
        sendItemListToOutput(items);

        UserCashier userCashier = createUserCashier();

    }

    private UserCashier createUserCashier() {
        while (true){
            try{
                return new UserCashier(Input.inputUSerAmount());
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


}
