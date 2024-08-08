package jumble_jump;

import jumble_jump.controller.GameController;

public class Application {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
