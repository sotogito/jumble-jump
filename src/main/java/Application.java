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
        mainController.main();
    }

}
