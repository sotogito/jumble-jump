package jumble_jump.controller;

import jumble_jump.repository.Numbers;
import jumble_jump.repository.Tokens369;
import jumble_jump.service.ClapMaker;
import jumble_jump.service.Game369Service;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

public class MainController {
    private Game369Service game369Service;


    public void main() {
        create369Service();

        make369GameResult();
        print369GameResult();
    }

    private void make369GameResult() {
        while (true) {
            try {
                game369Service.make369TokenResult(inputNumber());
                break;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private String inputNumber() {
        return Input.inputNumbers();
    }

    private void print369GameResult() {
        Output.print369GameResult(game369Service);
    }

    private void create369Service() {
        Numbers numbers = new Numbers();
        Tokens369 tokens369 = new Tokens369();
        ClapMaker clapMaker = new ClapMaker();

        game369Service = new Game369Service(clapMaker, numbers, tokens369);
    }


}
