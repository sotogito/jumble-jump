import controller.MainController;
import domain.item.Item;
import domain.item.Items;
import util.reader.ItemCsvReader;
import util.reader.RawCsvReader;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        MainController mainController = new MainController();
        registerVendingMachineItems();
        mainController.main();
    }

    public static void registerVendingMachineItems() throws IOException {
        ItemCsvReader csvReader = new ItemCsvReader(new RawCsvReader());
        String itemCsvPath = "/data/items.csv";

        List<Item> itemList = csvReader.readItems(itemCsvPath);
        Items items = new Items(itemList);
    }

}
