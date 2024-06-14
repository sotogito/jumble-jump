package alice_in_wonderland.main.controller;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.command.Command;
import alice_in_wonderland.main.domain.command.InputManager;
import alice_in_wonderland.main.domain.manager.GameManager;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.domain.number.RandomNumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import alice_in_wonderland.main.view.Input;
import alice_in_wonderland.main.view.Output;

public class WonderlandDevelop {

    public void main(){
        Output.printGameIntro();

        NumberMaker numberMaker = new RandomNumberMaker();
        Alice alice = new Alice(numberMaker);
        Door door = new Door(numberMaker);
        Key key = new Key();

        GameManager gameManager = new GameManager();
        InputManager inputManager = new InputManager(alice, door, key);

        Output.printSizeAndAttemptCount(gameManager,key,door,alice);
        boolean isGameEnd = gameLoop(inputManager,gameManager,key,door,alice);
        if (isGameEnd){
            Output.printSuccess(gameManager,alice);
            return;
        }
        Output.printFail(alice,door);

    }

    private boolean gameLoop(InputManager inputManager, GameManager gameManager,
                             Key key, Door door, Alice alice){

        while (!gameManager.isOverCount()){
            try{
                inputManager.registerCommand(getInput());
                Output.printSizeAndAttemptCount(gameManager, key, door, alice);
            }catch (IllegalArgumentException e){
                if(e.getMessage().contains("성공")) {
                    return true;
                }
                Output.printError(e.getMessage());
                if (!e.getMessage().contains(ErrorMessage.INPUT_ERROR)){
                    Output.printAttemptCount(gameManager);
                }
            }
        }
        return false;
    }

    private String getInput(){
        return Input.inputUser();
    }


}
