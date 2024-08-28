package jumble_jump.controller;

import jumble_jump.domain.NumberT;
import jumble_jump.repository.Numbers;
import jumble_jump.repository.Tokens369;
import jumble_jump.service.ClapMaker;
import jumble_jump.service.Game369Service;
import jumble_jump.util.InputNumberParser;
import jumble_jump.view.Input;

import java.util.List;

public class MainController {
    private Game369Service game369Service;



    public void main(){
        create369Service();

        while (true) {
            try{
                game369Service.make369TokenResult(Input.inputNumbers());
                break;
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }

        for(String printout : game369Service.getTokenResult()){
            System.out.println(printout);
        }
    }

    private void create369Service(){
        Numbers numbers = new Numbers();
        Tokens369 tokens369 = new Tokens369();
        ClapMaker clapMaker = new ClapMaker();

        game369Service = new Game369Service(clapMaker, numbers, tokens369);
    }


}
