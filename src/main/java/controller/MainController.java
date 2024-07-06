package controller;

import domain.Order;
import domain.item.Item;
import domain.item.Items;
import util.reader.ItemCsvReader;
import util.reader.RawCsvReader;

import java.io.IOException;
import java.util.List;

public class MainController {
    public void main() throws IOException {
        Items items = registerVendingMachineItems();
        System.out.println(items);

    }


    private Items registerVendingMachineItems() throws IOException {
        ItemCsvReader csvReader = new ItemCsvReader(new RawCsvReader());
        String itemCsvPath = "/data/items.csv";

        List<Item> itemList = csvReader.readItems(itemCsvPath);
        return new Items(itemList);
    }


}
