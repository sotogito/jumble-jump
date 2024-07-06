package view.printer;

import domain.item.Item;
import domain.item.Items;

public class ItemListPrinter implements Printer{
    private static final String PRINT_ITEM_LIST_INTRO = "<구매 가능 상품 목록>\n";
    private static final String PRINT_ITEM_LIST_FORMAT = "- %s : %,d원\n";
    private final Items items;

    public ItemListPrinter(Items items) {
        this.items = items;
    }

    @Override
    public void print(){
        System.out.println();
        System.out.print(PRINT_ITEM_LIST_INTRO);
        System.out.print(getItemList());

    }

    private String getItemList(){
        StringBuilder sb = new StringBuilder();
        for(Item item : items.getItems()){
            String printout = String.format(PRINT_ITEM_LIST_FORMAT, item.getName(), item.getPrice());
            sb.append(printout);
        }
        return sb.toString();
    }

}
