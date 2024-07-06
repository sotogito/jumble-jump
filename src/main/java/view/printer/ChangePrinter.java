package view.printer;

import domain.Bill;
import domain.UserCashier;

import java.util.Map;

public class ChangePrinter {
    private static final String PRINT_CHANGE_INTRO ="<잔돈>\n";
    private static final String PRINT_CHANGE_FORMAT ="%,d원 - %d개\n";

    private final UserCashier userCashier;

    public ChangePrinter(UserCashier userCashier) {
        this.userCashier = userCashier;
    }

    public void print(){
        System.out.print(PRINT_CHANGE_INTRO);
        System.out.print(getChange());
    }

    private String getChange(){
        StringBuilder sb = new StringBuilder();

        Map<Bill, Integer> change = userCashier.getChange();
        for (Map.Entry<Bill, Integer> entry : change.entrySet()) {
            int coinValue = entry.getKey().getValue();
            int count = entry.getValue();

            String printout = String.format(PRINT_CHANGE_FORMAT,coinValue,count);
            sb.append(printout);
        }
        return sb.toString();
    }




}
