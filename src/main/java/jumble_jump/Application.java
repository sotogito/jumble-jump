package jumble_jump;

import jumble_jump.controller.MainController;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        MainController mainController = new MainController();
        mainController.main();
    }
}
