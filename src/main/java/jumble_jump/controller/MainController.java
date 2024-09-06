package jumble_jump.controller;

import jumble_jump.domain.Cutter;
import jumble_jump.domain.CutterControlUnit;
import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import jumble_jump.serive.CutterHeightSettingService;
import jumble_jump.util.InputRiceCakeParser;
import jumble_jump.util.OrderFactory;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.util.List;

public class MainController {
    private CutterHeightSettingService cutterHeightSettingService;

    public void main() {
        Order order = createOrder();
        createCutterHeightSettingService(order);

        cutterHeightSettingService.setCutterHeight();

        printCutterHeightResult();
    }

    private void createCutterHeightSettingService(Order order) {
        cutterHeightSettingService = new CutterHeightSettingService(new Cutter(), order, new CutterControlUnit());
    }

    private Order createOrder() {
        while (true) {
            try {
                List<RiceCake> riceCakeeList = InputRiceCakeParser.parse(Input.inputRiceCakes());
                int totalHeight = Input.inputRiceCakeTotalHeight();

                return OrderFactory.createOrder(riceCakeeList, totalHeight);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printCutterHeightResult() {
        Output.printCutterHeightResult(cutterHeightSettingService);
    }

}
