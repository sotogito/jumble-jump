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
        ItemCsvReader csvReader = new ItemCsvReader(new RawCsvReader());
        String itemCsvPath = "/data/items.csv";

        List<Item> itemList = csvReader.readItems(itemCsvPath);
        Items items = new Items(itemList);

        System.out.println(items.findItemByName("씹던 껌").getName());

    }
}
