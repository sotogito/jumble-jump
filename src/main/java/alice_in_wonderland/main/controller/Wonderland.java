package alice_in_wonderland.main.controller;

import alice_in_wonderland.main.domain.charactor.Alice;
import alice_in_wonderland.main.domain.charactor.Door;
import alice_in_wonderland.main.domain.charactor.Key;
import alice_in_wonderland.main.domain.manager.GameManager;
import alice_in_wonderland.main.domain.manager.InputMatcher;
import alice_in_wonderland.main.domain.number.NumberMaker;
import alice_in_wonderland.main.domain.number.RandomNumberMaker;
import alice_in_wonderland.main.util.message.ErrorMessage;
import alice_in_wonderland.main.util.message.GameMessage;
import alice_in_wonderland.main.view.Input;
import alice_in_wonderland.main.view.Output;

public class Wonderland {

    public void main(){
        Output.printGameIntro();

        Alice alice = createAlice();
        Door door = createDoor();
        Key key = createKey();
        GameManager gameManager = new GameManager();
        Output.printAttemptCount(gameManager);
        Output.printCharacterSize(key,door,alice);



        try{
            while (true){
                InputMatcher inputMatcher = createInputMatcher(gameManager,alice, door, key);
                gameManager.decreaseCount();
                if (gameManager.isGameOver(key,door)){
                    Output.printSuccess(gameManager,alice);
                    break;
                }
                Output.printAttemptCount(gameManager);
                Output.printCharacterSize(key,door,alice);
            }
        }catch (IllegalArgumentException e){
            Output.printFail(alice,door);
        }




        System.out.println("끝났다.");


    }

    //잘못 입력했을때 = 시도횟수 x
    //다른 오류 = 시도횟수o
    private InputMatcher createInputMatcher(GameManager gameManager,Alice alice, Door door, Key key) {
        while (!gameManager.isOverCount()){
            try{
                return new InputMatcher(getUserInput(),alice,door,key);
            }catch (IllegalArgumentException e) {
                if(e.getMessage().contains(ErrorMessage.INPUT_ERROR)){
                    System.out.println("\n"+e.getMessage());
                    continue;
                }
                gameManager.decreaseCount();
                System.out.println("\n"+e.getMessage());
                Output.printAttemptCount(gameManager);
            }
        }
        throw new IllegalArgumentException(); //시도 횟수를 초과했을 때
    }




    private String getUserInput(){
        return Input.inputUser();
    }


    private Alice createAlice(){
        NumberMaker numberMaker = new RandomNumberMaker();
        return new Alice(numberMaker);
    }

    private Door createDoor(){
        NumberMaker numberMaker = new RandomNumberMaker();
        return new Door(numberMaker);
    }

    private Key createKey(){
        return new Key();
    }




}
