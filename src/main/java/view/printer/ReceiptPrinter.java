package view.printer;

import domain.Order;
import domain.UserCashier;
import domain.item.Item;
import util.message.ServiceMessage;

import java.util.Map;

public class ReceiptPrinter implements Printer {
    private static final String PRINT_RECEIPT_INTRO_MESSAGE = "영수증을 출력할게요.\n";
    private static final String PRINT_RECEIPT_INTRO = "<영수증>\n";
    private static final String PRINT_ITEM_RECEIPT_FORMAT = "%s, %d개, %,d원\n";
    private static final String PRINT_TOTAL_AMOUNT = "총 가격 : %,d원\n";
    private static final String DIVIDER = "------\n";

    private final UserCashier userCashier;
    private final Order order;

    public ReceiptPrinter(UserCashier userCashier, Order order) {
        this.userCashier = userCashier;
        this.order = order;
    }

    @Override
    public void print(){
        System.out.print(PRINT_RECEIPT_INTRO_MESSAGE);
        System.out.println();
        System.out.print(PRINT_RECEIPT_INTRO);
        System.out.printf(ServiceMessage.PRINT_BALANCE,getChange());
        System.out.print(DIVIDER);
        System.out.print(getPurchaseItemReceipt());
        System.out.print(DIVIDER);
        System.out.printf(PRINT_TOTAL_AMOUNT,getTotalPurchaseAmount());
    }

    private long getChange(){
        return userCashier.getAmount();
    }

    private String getPurchaseItemReceipt(){
        StringBuilder sb = new StringBuilder();

        Map<Item, Integer> purchaseItems = order.getPurchasedItems();
        for (Map.Entry<Item, Integer> entry : purchaseItems.entrySet()) {
            String name = entry.getKey().getName();
            int quantity = entry.getValue();
            long price = entry.getKey().calculatePurchasePriceAsAmount(entry.getValue());

            String printout = String.format(PRINT_ITEM_RECEIPT_FORMAT,name,quantity,price);
            sb.insert(0,printout);
        }

        return sb.toString();
    }

    private long getTotalPurchaseAmount(){
        return order.getTotalAmount();
    }

}
